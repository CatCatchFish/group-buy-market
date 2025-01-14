package cn.cat.domain.trade.service;

import cn.cat.domain.trade.model.entity.MarketPayOrderEntity;
import cn.cat.domain.trade.model.entity.PayActivityEntity;
import cn.cat.domain.trade.model.entity.PayDiscountEntity;
import cn.cat.domain.trade.model.entity.UserEntity;
import cn.cat.domain.trade.model.valobj.GroupBuyProgressVO;

public interface ITradeOrderService {

    /**
     * 查询，未被支付消费完成的营销优惠订单
     *
     * @param userId     用户ID
     * @param outTradeNo 外部唯一单号
     * @return 拼团，预购订单营销实体对象
     */
    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    /**
     * @param user        用户实体对象
     * @param payActivity 拼团支付活动实体
     * @param payDiscount 拼团折扣实体
     * @return 拼团，预购订单营销实体对象
     */
    MarketPayOrderEntity lockMarketPayOrder(UserEntity user, PayActivityEntity payActivity, PayDiscountEntity payDiscount);

    /**
     * @param teamId 拼团ID
     * @return 拼团进度对象
     */
    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

}
