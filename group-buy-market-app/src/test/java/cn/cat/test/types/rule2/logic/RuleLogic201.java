package cn.cat.test.types.rule2.logic;

import cn.cat.test.types.rule2.factory.Rule02TradeRuleFactory;
import cn.cat.types.design.framework.link.model2.handler.ILogicHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RuleLogic201 implements ILogicHandler<String, Rule02TradeRuleFactory.DynamicContext, XxxResponse> {

    @Override
    public XxxResponse apply(String requestParameter, Rule02TradeRuleFactory.DynamicContext dynamicContext) throws Exception {

        log.info("link model02 RuleLogic201");

        return next(requestParameter, dynamicContext);
    }

}
