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
public class CrowdTags {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 标签id
     */
    private String tagId;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 标签统计量
     */
    private Integer statistics;
    /**
     * 标签描述
     */
    private String tagDesc;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
