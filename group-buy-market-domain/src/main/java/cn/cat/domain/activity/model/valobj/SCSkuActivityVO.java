package cn.cat.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SCSkuActivityVO {

    /**
     * 渠道
     */
    private String source;
    /**
     * 来源
     */
    private String chanel;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 商品ID
     */
    private String goodsId;

}
