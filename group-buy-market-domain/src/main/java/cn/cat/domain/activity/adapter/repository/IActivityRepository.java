package cn.cat.domain.activity.adapter.repository;

import cn.cat.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.cat.domain.activity.model.valobj.SkuVO;

public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel);

    SkuVO querySkuByGoodsId(String goodsId);

}
