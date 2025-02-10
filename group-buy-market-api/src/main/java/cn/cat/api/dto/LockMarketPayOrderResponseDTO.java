package cn.cat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LockMarketPayOrderResponseDTO {

    /**
     * 预购订单ID
     */
    private String orderId;
    /**
     * 原价格
     */
    private BigDecimal originalPrice;
    /**
     * 折扣金额
     */
    private BigDecimal deductionPrice;
    /**
     * 支付金额
     */
    private BigDecimal payPrice;
    /**
     * 交易订单状态
     */
    private Integer tradeOrderStatus;

}
