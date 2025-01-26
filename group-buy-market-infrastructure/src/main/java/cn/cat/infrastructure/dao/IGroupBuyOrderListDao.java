package cn.cat.infrastructure.dao;

import cn.cat.infrastructure.dao.po.GroupBuyOrderList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IGroupBuyOrderListDao {

    GroupBuyOrderList queryGroupBuyOrderRecordByOutTradeNo(GroupBuyOrderList groupBuyOrderList);

    void insert(GroupBuyOrderList groupBuyOrderList);

    Integer queryOrderCountByActivityId(GroupBuyOrderList groupBuyOrderListReq);

}
