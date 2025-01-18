package cn.cat.types.design.framework.link.model2;

import cn.cat.types.design.framework.link.model2.chain.BusinessLinkedList;
import cn.cat.types.design.framework.link.model2.handler.ILogicHandler;
import lombok.Getter;

@Getter
public class LinkArmory<T, D, R> {

    private final BusinessLinkedList<T, D, R> logicLink;

    @SafeVarargs
    public LinkArmory(String linkName, ILogicHandler<T, D, R>... logicHandlers) {
        logicLink = new BusinessLinkedList<>(linkName);
        for (ILogicHandler<T, D, R> logicHandler : logicHandlers) {
            logicLink.add(logicHandler);
        }
    }

}
