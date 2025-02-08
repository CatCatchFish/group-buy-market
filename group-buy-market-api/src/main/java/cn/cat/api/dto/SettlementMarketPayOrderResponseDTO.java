package cn.cat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementMarketPayOrderResponseDTO {

    private String userId;
    private String teamId;
    private Long activityId;
    private String outTradeNo;

}
