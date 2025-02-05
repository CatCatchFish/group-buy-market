package cn.cat.domain.activity.adapter.repository;

import cn.cat.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import cn.cat.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.cat.domain.activity.model.valobj.SCSkuActivityVO;
import cn.cat.domain.activity.model.valobj.SkuVO;
import cn.cat.domain.activity.model.valobj.TeamStatisticVO;

import java.util.List;

public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);

    SkuVO querySkuByGoodsId(String goodsId);

    SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId);

    boolean isTagCrowdRange(String tagId, String userId);

    boolean downgradeSwitch();

    boolean cutRange(String userId);

    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByOwner(Long activityId, String userId, Integer ownerCount);

    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByRandom(Long activityId, String userId, Integer randomCount);

    TeamStatisticVO queryTeamStatisticByActivityId(Long activityId);

}
