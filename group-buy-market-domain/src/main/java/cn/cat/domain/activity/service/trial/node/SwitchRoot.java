package cn.cat.domain.activity.service.trial.node;

import cn.cat.domain.activity.model.entity.MarketProductEntity;
import cn.cat.domain.activity.model.entity.TrialBalanceEntity;
import cn.cat.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import cn.cat.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import cn.cat.types.design.framework.tree.StrategyHandler;
import cn.cat.types.enums.ResponseCode;
import cn.cat.types.exception.AppException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SwitchRoot extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private MarketNode marketNode;

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-SwitchNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));

        // 根据用户ID切量
        String userId = requestParameter.getUserId();
        // 判断是否降级
        if (repository.downgradeSwitch()) {
            log.info("拼团活动降级拦截 {}", userId);
            throw new AppException(ResponseCode.E0003.getCode(), ResponseCode.E0003.getInfo());
        }

        // 切量范围判断
        if (!repository.cutRange(userId)) {
            log.info("拼团活动切量拦截 {}", userId);
            throw new AppException(ResponseCode.E0004.getCode(), ResponseCode.E0004.getInfo());
        }

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return marketNode;
    }

}
