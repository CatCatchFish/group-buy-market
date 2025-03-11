package cn.cat.domain.trade.service.settlement;

import cn.cat.domain.trade.adapter.port.ITradePort;
import cn.cat.domain.trade.adapter.repository.ITradeRepository;
import cn.cat.domain.trade.model.aggregate.GroupBuyTeamSettlementAggregate;
import cn.cat.domain.trade.model.entity.*;
import cn.cat.domain.trade.service.ITradeSettlementOrderService;
import cn.cat.domain.trade.service.settlement.factory.TradeSettlementRuleFilterFactory;
import cn.cat.types.design.framework.link.model2.chain.BusinessLinkedList;
import cn.cat.types.enums.NotifyTaskHTTPEnumVO;
import cn.cat.types.util.FutureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TradeSettlementOrderService implements ITradeSettlementOrderService {

    @Resource
    private ITradeRepository repository;
    @Resource
    private ITradePort port;
    @Resource
    private BusinessLinkedList<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> tradeSettlementRuleFilter;
    @Resource
    private FutureUtil futureUtil;

    @Override
    public TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity) throws Exception {
        log.info("拼团交易-支付订单结算:{} outTradeNo:{}", tradePaySuccessEntity.getUserId(), tradePaySuccessEntity.getOutTradeNo());

        // 1. 结算规则过滤
        TradeSettlementRuleFilterBackEntity tradeSettlementRuleFilterBackEntity = tradeSettlementRuleFilter.apply(
                TradeSettlementRuleCommandEntity.builder()
                        .source(tradePaySuccessEntity.getSource())
                        .channel(tradePaySuccessEntity.getChannel())
                        .userId(tradePaySuccessEntity.getUserId())
                        .outTradeNo(tradePaySuccessEntity.getOutTradeNo())
                        .outTradeTime(tradePaySuccessEntity.getOutTradeTime())
                        .build(),
                new TradeSettlementRuleFilterFactory.DynamicContext());

        String teamId = tradeSettlementRuleFilterBackEntity.getTeamId();

        // 2. 查询组团信息
        GroupBuyTeamEntity groupBuyTeamEntity = GroupBuyTeamEntity.builder()
                .teamId(tradeSettlementRuleFilterBackEntity.getTeamId())
                .activityId(tradeSettlementRuleFilterBackEntity.getActivityId())
                .targetCount(tradeSettlementRuleFilterBackEntity.getTargetCount())
                .completeCount(tradeSettlementRuleFilterBackEntity.getCompleteCount())
                .lockCount(tradeSettlementRuleFilterBackEntity.getLockCount())
                .status(tradeSettlementRuleFilterBackEntity.getStatus())
                .validStartTime(tradeSettlementRuleFilterBackEntity.getValidStartTime())
                .validEndTime(tradeSettlementRuleFilterBackEntity.getValidEndTime())
                .notifyUrl(tradeSettlementRuleFilterBackEntity.getNotifyUrl())
                .build();

        // 3. 构建聚合对象
        GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate = GroupBuyTeamSettlementAggregate.builder()
                .userEntity(UserEntity.builder().userId(tradePaySuccessEntity.getUserId()).build())
                .groupBuyTeamEntity(groupBuyTeamEntity)
                .tradePaySuccessEntity(tradePaySuccessEntity)
                .build();

        // 4. 拼团交易结算
        repository.settlementMarketPayOrder(groupBuyTeamSettlementAggregate);

        // 5. 组队回调处理 - 处理失败也会有定时任务补偿，通过这样的方式，可以减轻任务调度，提高时效性
        execSettlementNotifyJob(teamId);

        // 6. 返回结算信息 - 公司中开发这样的流程时候，会根据外部需要进行值的设置
        return TradePaySettlementEntity.builder()
                .source(tradePaySuccessEntity.getSource())
                .channel(tradePaySuccessEntity.getChannel())
                .userId(tradePaySuccessEntity.getUserId())
                .teamId(teamId)
                .activityId(groupBuyTeamEntity.getActivityId())
                .outTradeNo(tradePaySuccessEntity.getOutTradeNo())
                .build();
    }

    @Override
    public void execSettlementNotifyJob() throws Exception {
        // 查询未执行任务
        List<NotifyTaskEntity> notifyTaskEntityList = repository.queryUnExecutedNotifyTaskList();
        if (notifyTaskEntityList.isEmpty()) return;
        log.info("拼团交易-执行结算通知任务");
        execSettlementNotifyJob(notifyTaskEntityList);
    }

    @Override
    public void execSettlementNotifyJob(String teamId) throws Exception {
        log.info("拼团交易-执行结算通知回调，指定 teamId:{}", teamId);
        List<NotifyTaskEntity> notifyTaskEntityList = repository.queryUnExecutedNotifyTaskList(teamId);
        execSettlementNotifyJob(notifyTaskEntityList);
    }

    private void execSettlementNotifyJob(List<NotifyTaskEntity> notifyTaskEntityList) throws Exception {
        if (null == notifyTaskEntityList || notifyTaskEntityList.isEmpty()) return;
        for (NotifyTaskEntity notifyTask : notifyTaskEntityList) {
            int retryCount = notifyTask.getNotifyCount();
            long retryTime = (long) Math.pow(2, retryCount);
            // 回调处理 success 成功，error 失败
            CompletableFuture<String> future = futureUtil.supplyAsync(retryTime, TimeUnit.SECONDS, NotifyTaskHTTPEnumVO.ERROR.getCode(), () -> {
                try {
                    return port.groupBuyNotify(notifyTask);
                } catch (Exception e) {
                    log.error("拼团交易-执行结算通知回调异常，teamId:{}, notifyUrl:{}, parameterJson:{}",
                            notifyTask.getTeamId(), notifyTask.getNotifyUrl(), notifyTask.getParameterJson(), e);
                    return NotifyTaskHTTPEnumVO.ERROR.getCode();
                }
            });
            future.thenAccept(result -> {
                if (NotifyTaskHTTPEnumVO.SUCCESS.getCode().equals(result)) {
                    // 成功，更新通知任务状态
                    repository.updateNotifyTaskStatusSuccess(notifyTask.getTeamId());
                } else if (NotifyTaskHTTPEnumVO.ERROR.getCode().equals(result)) {
                    if (retryCount < 5) {
                        // 重试次数小于5次，继续重试
                        repository.updateNotifyTaskStatusRetry(notifyTask.getTeamId());
                    } else {
                        // 重试次数大于等于5次，更新通知任务状态
                        repository.updateNotifyTaskStatusError(notifyTask.getTeamId());
                    }
                }
            });
        }
    }

}
