package cn.cat.domain.tag.service;

public interface ITagService {

    void execTagBatchJob(String tagId, String batchId);

    void addToTag(String tagId, String userId);

}
