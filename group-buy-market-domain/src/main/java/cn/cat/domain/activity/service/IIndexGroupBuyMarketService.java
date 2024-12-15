package cn.cat.domain.activity.service;

import cn.cat.domain.activity.model.entity.MarketProductEntity;
import cn.cat.domain.activity.model.entity.TrialBalanceEntity;

/**
 * @description 首页营销服务接口
 */
public interface IIndexGroupBuyMarketService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;

}
