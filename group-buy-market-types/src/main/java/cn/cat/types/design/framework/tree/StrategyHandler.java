package cn.cat.types.design.framework.tree;

/**
 * @description 策略处理器
 * T 入参类型
 * D 上下文参数
 * R 返参类型
 */
public interface StrategyHandler<T, D, R> {

    StrategyHandler DEFAULT = (T, D) -> null;

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
