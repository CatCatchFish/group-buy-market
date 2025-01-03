package cn.cat.domain.activity.adapter.repository;

import cn.cat.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.cat.domain.activity.model.valobj.SCSkuActivityVO;
import cn.cat.domain.activity.model.valobj.SkuVO;

public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);

    SkuVO querySkuByGoodsId(String goodsId);

    SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId);

    boolean isTagCrowdRange(String tagId, String userId);

}
