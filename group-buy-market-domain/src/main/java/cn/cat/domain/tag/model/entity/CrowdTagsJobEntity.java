package cn.cat.domain.tag.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrowdTagsJobEntity {

    /**
     * 标签类型（参与量、消费金额）
     */
    private Integer tagType;
    /**
     * 标签规则
     */
    private String tagRule;
    /**
     * 统计数据开始时间
     */
    private Date statStartTime;
    /**
     * 统计数据结束时间
     */
    private Date statEndTime;

}
