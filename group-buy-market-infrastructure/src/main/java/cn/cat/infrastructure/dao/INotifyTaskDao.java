package cn.cat.infrastructure.dao;

import cn.cat.infrastructure.dao.po.NotifyTask;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface INotifyTaskDao {

    void insert(NotifyTask notifyTask);

}
