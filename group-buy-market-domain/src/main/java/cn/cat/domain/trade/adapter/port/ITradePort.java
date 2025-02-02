package cn.cat.domain.trade.adapter.port;

import cn.cat.domain.trade.model.entity.NotifyTaskEntity;

/**
 * @description 交易接口服务接口
 */
public interface ITradePort {

    String groupBuyNotify(NotifyTaskEntity notifyTask) throws Exception;

}
