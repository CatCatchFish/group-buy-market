package cn.cat.domain.trade.service.settlement.filter;

import cn.cat.domain.trade.adapter.repository.ITradeRepository;
import cn.cat.domain.trade.model.entity.MarketPayOrderEntity;
import cn.cat.domain.trade.model.entity.TradeSettlementRuleCommandEntity;
import cn.cat.domain.trade.model.entity.TradeSettlementRuleFilterBackEntity;
import cn.cat.domain.trade.service.settlement.factory.TradeSettlementRuleFilterFactory;
import cn.cat.types.design.framework.link.model2.handler.ILogicHandler;
import cn.cat.types.enums.ResponseCode;
import cn.cat.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class OutTradeNoRuleFilter implements ILogicHandler<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeSettlementRuleFilterBackEntity apply(TradeSettlementRuleCommandEntity requestParameter, TradeSettlementRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("结算规则过滤-外部单号校验{} outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());

        // 查询拼团信息
        MarketPayOrderEntity marketPayOrderEntity = repository.queryMarketPayOrderEntityByOutTradeNo(requestParameter.getUserId(), requestParameter.getOutTradeNo());
        if (null == marketPayOrderEntity) {
            log.error("不存在的外部交易单号或用户已退单，不需要做支付订单结算:{} outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
            throw new AppException(ResponseCode.E0104);
        }

        dynamicContext.setMarketPayOrderEntity(marketPayOrderEntity);
        return next(requestParameter, dynamicContext);
    }

}
