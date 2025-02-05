package cn.cat.infrastructure.dao;

import cn.cat.infrastructure.dao.po.GroupBuyOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface IGroupBuyOrderDao {

    void insert(GroupBuyOrder groupBuyOrder);

    int updateAddLockCount(String teamId);

    GroupBuyOrder queryGroupBuyProgress(String teamId);

    GroupBuyOrder queryGroupBuyTeamByTeamId(String teamId);

    int updateAddCompleteCount(String teamId);

    int updateOrderStatus2COMPLETE(String teamId);

    List<GroupBuyOrder> queryGroupBuyProgressByTeamIds(Set<String> teamIds);

    Integer queryAllTeamCount(Set<String> teamIds);

    Integer queryAllTeamCompleteCount(Set<String> teamIds);

    Integer queryAllTeamUserCount(Set<String> teamIds);

}
