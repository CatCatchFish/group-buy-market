package cn.cat.domain.trade.model.aggregate;

import cn.cat.domain.trade.model.entity.PayActivityEntity;
import cn.cat.domain.trade.model.entity.PayDiscountEntity;
import cn.cat.domain.trade.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyOrderAggregate {

    private UserEntity userEntity;
    private PayActivityEntity payActivityEntity;
    private PayDiscountEntity payDiscountEntity;
    private Integer userTakeOrderCount;

}
