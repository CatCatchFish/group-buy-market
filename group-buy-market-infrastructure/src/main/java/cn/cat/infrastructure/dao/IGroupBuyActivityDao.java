package cn.cat.infrastructure.dao;

import cn.cat.infrastructure.dao.po.GroupBuyActivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author cat
 * @description 拼团活动Dao
 */
@Mapper
public interface IGroupBuyActivityDao {

    GroupBuyActivity queryValidGroupBuyActivityId(Long activityId);

    GroupBuyActivity queryGroupBuyActivityByActivityId(Long activityId);

}
