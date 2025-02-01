package cn.cat.domain.trade.adapter.repository;

import cn.cat.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import cn.cat.domain.trade.model.aggregate.GroupBuyTeamSettlementAggregate;
import cn.cat.domain.trade.model.entity.GroupBuyActivityEntity;
import cn.cat.domain.trade.model.entity.GroupBuyTeamEntity;
import cn.cat.domain.trade.model.entity.MarketPayOrderEntity;
import cn.cat.domain.trade.model.valobj.GroupBuyProgressVO;

public interface ITradeRepository {

    MarketPayOrderEntity queryMarketPayOrderEntityByOutTradeNo(String userId, String outTradeNo);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    GroupBuyActivityEntity queryGroupBuyActivityEntityByActivityId(Long activityId);

    Integer queryOrderCountByActivityId(Long activityId, String userId);

    GroupBuyTeamEntity queryGroupBuyTeamByTeamId(String teamId);

    void settlementMarketPayOrder(GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate);

    boolean isSCBlackIntercept(String source, String channel);

}
