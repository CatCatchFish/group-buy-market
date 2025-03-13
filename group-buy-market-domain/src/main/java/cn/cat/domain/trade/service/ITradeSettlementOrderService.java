package cn.cat.domain.trade.service;

import cn.cat.domain.trade.model.entity.TradePaySettlementEntity;
import cn.cat.domain.trade.model.entity.TradePaySuccessEntity;

import java.util.Map;

/**
 * @description 拼团交易结算服务接口
 */
public interface ITradeSettlementOrderService {

    /**
     * 营销结算
     *
     * @param tradePaySuccessEntity 交易支付订单实体对象
     * @return 交易结算订单实体
     */
    TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity) throws Exception;

    /**
     * 执行结算通知任务
     *
     * @throws Exception 异常
     */
    void execSettlementNotifyJob() throws Exception;

    /**
     * 执行结算通知任务
     *
     * @param teamId 指定结算组ID
     * @throws Exception 异常
     */
    void execSettlementNotifyJob(String teamId) throws Exception;

}
