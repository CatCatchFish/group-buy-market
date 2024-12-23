package cn.cat.domain.activity.service.discount;

import cn.cat.domain.activity.model.valobj.DiscountTypeEnum;
import cn.cat.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {

    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        // 1. 人群过滤 - 限定人群优惠
        if (DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())) {
            boolean isCrowdRange = filterTagId(userId, groupBuyDiscount.getTagId());
            if (!isCrowdRange) {
                return originalPrice;
            }
        }
        // 2. 子类实现具体的折扣算法
        return doCalculate(originalPrice, groupBuyDiscount);
    }

    // 人群过滤 - 限定人群优惠
    private boolean filterTagId(String userId, String tagId) {
        // todo cat 后续开发这部分
        return true;
    }

    protected BigDecimal noLessThanZero(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) <= 0 ? new BigDecimal("0.01") : price;
    }

    // 子类实现具体的折扣算法
    protected abstract BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);

}
