package cn.cat.types.design.framework.link.model2.handler;

/**
 * 逻辑处理器接口
 *
 * @param <T> 参数
 * @param <D> 动态上下文
 * @param <R> 返回值
 */
public interface ILogicHandler<T, D, R> {

    default R next(T requestParameter, D dynamicContext) {
        return null;
    }

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
