package cn.cat.types.design.framework.link.model2.chain;

import cn.cat.types.design.framework.link.model2.handler.ILogicHandler;

public class BusinessLinkedList<T, D, R> extends LinkedList<ILogicHandler<T, D, R>> implements ILogicHandler<T, D, R> {

    public BusinessLinkedList(String name) {
        super(name);
    }

    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        Node<ILogicHandler<T, D, R>> current = this.first;
        do {
            ILogicHandler<T, D, R> item = current.item;
            R result = item.apply(requestParameter, dynamicContext);
            if (null != result) return result;
            current = current.next;
        } while (current != null);
        return null;
    }

}
