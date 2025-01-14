package cn.cat.infrastructure.dao;

import cn.cat.infrastructure.dao.po.GroupBuyOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IGroupBuyOrderDao {

    void insert(GroupBuyOrder groupBuyOrder);

    int updateAddLockCount(String teamId);

    GroupBuyOrder queryGroupBuyProgress(String teamId);

}
