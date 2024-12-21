package cn.cat.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketProductEntity {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 商品ID
     */
    private String goodsId;
    /**
     * 渠道
     */
    private String source;
    /**
     * 来源
     */
    private String channel;

    public boolean hasBlank() {
        return StringUtils.isBlank(userId) || StringUtils.isBlank(goodsId) || StringUtils.isBlank(source) || StringUtils.isBlank(channel);
    }

}