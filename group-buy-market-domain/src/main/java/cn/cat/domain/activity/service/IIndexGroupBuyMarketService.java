package cn.cat.domain.activity.service;

import cn.cat.domain.activity.model.entity.MarketProductEntity;
import cn.cat.domain.activity.model.entity.TrialBalanceEntity;
import cn.cat.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import cn.cat.domain.activity.model.valobj.TeamStatisticVO;

import java.util.List;

/**
 * @description 首页营销服务接口
 */
public interface IIndexGroupBuyMarketService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;

    /**
     * 查询进行中的拼团订单
     *
     * @param activityId  活动ID
     * @param userId      用户ID
     * @param ownerCount  个人数量
     * @param randomCount 随机数量
     * @return 用户拼团明细数据
     */
    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailList(Long activityId, String userId, Integer ownerCount, Integer randomCount);

    TeamStatisticVO queryTeamStatisticByActivityId(Long activityId);

}
