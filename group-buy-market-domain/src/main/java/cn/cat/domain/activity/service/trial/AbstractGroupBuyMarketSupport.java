package cn.cat.domain.activity.service.trial;

import cn.cat.domain.activity.adapter.repository.IActivityRepository;
import cn.cat.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import cn.cat.types.design.framework.tree.AbstractMultiThreadStrategyRouter;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<cn.cat.domain.activity.model.entity.MarketProductEntity,
        DefaultActivityStrategyFactory.DynamicContext,
        cn.cat.domain.activity.model.entity.TrialBalanceEntity> {
    protected long timeout = 500;
    @Resource
    protected IActivityRepository repository;

    @Override
    protected void multiThread(cn.cat.domain.activity.model.entity.MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {

    }
}
