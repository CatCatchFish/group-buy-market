package cn.cat.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrowdTagsJob {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 标签id
     */
    private String tagId;
    /**
     * 批次id
     */
    private String batchId;
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
    /**
     * 状态；0初始、1计划（进入执行阶段）、2重置、3完成
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
