package cn.cat.domain.trade.service;

import cn.cat.domain.trade.model.entity.TradePaySettlementEntity;
import cn.cat.domain.trade.model.entity.TradePaySuccessEntity;

/**
 * @description 拼团交易结算服务接口
 */
public interface ITradeSettlementOrderService {

    TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity) throws Exception ;

}
