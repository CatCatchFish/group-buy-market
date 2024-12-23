package cn.cat.domain.activity.service.discount.impl;

import cn.cat.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import cn.cat.domain.activity.service.discount.AbstractDiscountCalculateService;
import cn.cat.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 满减折扣计算服务
 */
@Slf4j
@Service("MJ")
public class MJCalculateService extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("优惠策略折扣计算:{}", groupBuyDiscount.getDiscountType().getCode());
        String marketExpr = groupBuyDiscount.getMarketExpr();

        String[] split = marketExpr.split(Constants.SPLIT);
        BigDecimal x = new BigDecimal(split[0]);
        BigDecimal y = new BigDecimal(split[1]);

        // 如果原价小于满减门槛，则直接返回原价
        if (originalPrice.compareTo(x) < 0) {
            return originalPrice;
        }

        BigDecimal discount = originalPrice.subtract(y);
        // 至少为 0.01 元
        return noLessThanZero(discount);
    }

}
