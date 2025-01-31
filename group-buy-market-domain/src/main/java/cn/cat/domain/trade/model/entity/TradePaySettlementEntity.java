package cn.cat.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradePaySettlementEntity {

    private String source;
    private String channel;
    private String userId;
    private String teamId;
    private Long activityId;
    private String outTradeNo;


}
