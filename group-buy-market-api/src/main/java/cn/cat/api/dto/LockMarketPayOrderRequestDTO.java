package cn.cat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LockMarketPayOrderRequestDTO {

    // 用户ID
    private String userId;
    // 拼单组队ID - 可为空，为空则创建新组队ID
    private String teamId;
    // 活动ID
    private Long activityId;
    // 商品ID
    private String goodsId;
    // 渠道
    private String source;
    // 来源
    private String channel;
    // 外部交易单号
    private String outTradeNo;
    // 回调地址
    private String notifyUrl;
    // 通知配置
    private NotifyConfigVO notifyConfigVO;

    public boolean notifyBlank() {
        if (notifyConfigVO == null)
            return true;
        if (notifyConfigVO.getNotifyType().equals("HTTP")) {
            return StringUtils.isBlank(notifyConfigVO.getNotifyUrl());
        } else if (notifyConfigVO.getNotifyType().equals("MQ")) {
            return StringUtils.isBlank(notifyConfigVO.getNotifyMQ());
        }
        return true;
    }

    // 兼容配置
    public void setNotifyUrl(String url) {
        NotifyConfigVO notifyConfigVO = new NotifyConfigVO();
        notifyConfigVO.setNotifyType("HTTP");
        notifyConfigVO.setNotifyUrl(url);
        this.notifyConfigVO = notifyConfigVO;
    }

    // 兼容配置 - MQ不需要指定，系统会发统一MQ消息
    public void setNotifyMQ() {
        NotifyConfigVO notifyConfigVO = new NotifyConfigVO();
        notifyConfigVO.setNotifyType("MQ");
        this.notifyConfigVO = notifyConfigVO;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotifyConfigVO {
        // 通知方式
        private String notifyType;
        // 通知地址
        private String notifyUrl;
        // 回调消息
        private String notifyMQ;
    }

}
