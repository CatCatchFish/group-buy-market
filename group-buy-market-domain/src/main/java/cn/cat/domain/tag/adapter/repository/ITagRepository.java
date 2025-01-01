package cn.cat.domain.tag.adapter.repository;

import cn.cat.domain.tag.model.entity.CrowdTagsJobEntity;

/**
 * 人群标签仓储接口
 */
public interface ITagRepository {

    /**
     * 查询人群标签任务实体
     *
     * @param tagId   人群标签ID
     * @param batchId 批次ID
     * @return 人群标签任务实体
     */
    CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId);

    /**
     * 添加用户Id到人群标签任务
     *
     * @param tagId  人群标签ID
     * @param userId 用户ID
     */
    void addCrowdTagsUserId(String tagId, String userId);

    /**
     * 更新人群标签任务统计数据
     *
     * @param tagId 人群标签ID
     * @param count 统计数据
     */
    void updateCrowdTagsStatistics(String tagId, int count);

}
