package cn.cat.infrastructure.adapter.repository;

import cn.cat.domain.tag.adapter.repository.ITagRepository;
import cn.cat.domain.tag.model.entity.CrowdTagsJobEntity;
import cn.cat.infrastructure.dao.ICrowdTagsDao;
import cn.cat.infrastructure.dao.ICrowdTagsDetailDao;
import cn.cat.infrastructure.dao.ICrowdTagsJobDao;
import cn.cat.infrastructure.dao.po.CrowdTags;
import cn.cat.infrastructure.dao.po.CrowdTagsDetail;
import cn.cat.infrastructure.dao.po.CrowdTagsJob;
import cn.cat.infrastructure.redis.RedissonService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBitSet;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Slf4j
@Repository
public class TagRepository implements ITagRepository {

    @Resource
    private ICrowdTagsDao crowdTagsDao;
    @Resource
    private ICrowdTagsDetailDao crowdTagsDetailDao;
    @Resource
    private ICrowdTagsJobDao crowdTagsJobDao;
    @Resource
    private RedissonService redisService;

    @Override
    public CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId) {
        CrowdTagsJob crowdTagsJobReq = CrowdTagsJob.builder()
                .tagId(tagId)
                .batchId(batchId)
                .build();

        CrowdTagsJob crowdTagsJob = crowdTagsJobDao.queryCrowdTagsJob(crowdTagsJobReq);
        if (null == crowdTagsJob) return null;

        return CrowdTagsJobEntity.builder()
                .tagType(crowdTagsJob.getTagType())
                .tagRule(crowdTagsJob.getTagRule())
                .statStartTime(crowdTagsJob.getStatStartTime())
                .statEndTime(crowdTagsJob.getStatEndTime())
                .build();
    }

    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {
        CrowdTagsDetail crowdTagsDetailReq = CrowdTagsDetail.builder()
                .tagId(tagId)
                .userId(userId)
                .build();

        try {
            crowdTagsDetailDao.addCrowdTagsUserId(crowdTagsDetailReq);
            // 获取BitSet
            RBitSet bitSet = redisService.getBitSet(tagId);
            bitSet.set(redisService.getIndexFromUserId(userId), true);
        } catch (DuplicateKeyException e) {
            // 重复插入忽略
        }
    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, int count) {
        CrowdTags crowdTagsReq = CrowdTags.builder()
                .tagId(tagId)
                .statistics(count)
                .build();

        crowdTagsDao.updateCrowdTagsStatistics(crowdTagsReq);
    }

}
