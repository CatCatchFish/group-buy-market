package cn.cat.types.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Component
public class FutureUtil {

    // 最大超时时间
    private static final int TIMEOUT_VALUE = 1500;
    // 时间单位
    private static final TimeUnit TIMEOUT_UNIT = TimeUnit.MILLISECONDS;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    // 超时调度线程池
    @Resource(name = "delayer")
    private ScheduledThreadPoolExecutor delayer;

    /**
     * 延迟执行任务
     *
     * @param command 要执行的任务（不可为null）
     * @param delay   延迟时间
     * @param unit    时间单位
     * @return {@link ScheduledFuture} 可用于取消任务或检查执行状态
     */
    public ScheduledFuture<?> delay(Runnable command, long delay, TimeUnit unit) {
        return delayer.schedule(command, delay, unit);
    }

    /**
     * 提交异步任务（默认超时时间）
     *
     * @param supplier 异步任务逻辑（不可为null）
     * @return {@link CompletableFuture} 任务结果或超时返回null
     */
    public <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        return supplyAsync(TIMEOUT_VALUE, TIMEOUT_UNIT, supplier);
    }

    /**
     * 提交异步任务（自定义超时时间）
     *
     * @param timeout  超时时间（需 > 0）
     * @param unit     时间单位（不可为null）
     * @param supplier 异步任务逻辑（不可为null）
     * @return {@link CompletableFuture} 任务结果或超时返回null
     */
    public <T> CompletableFuture<T> supplyAsync(long timeout, TimeUnit unit, Supplier<T> supplier) {
        CompletableFuture<T> origin = CompletableFuture.supplyAsync(supplier, threadPoolExecutor);
        CompletableFuture<T> timeoutFuture = timeoutAfter(timeout, unit);
        return origin.applyToEither(timeoutFuture, Function.identity())
                .exceptionally(throwable -> {
                    origin.cancel(true);
                    // 超时异常
                    log.error(throwable.getMessage());
                    return null;
                });
    }

    /**
     * 提交异步任务（自定义超时时间与错误返回值）
     *
     * @param timeout    超时时间（需 > 0）
     * @param unit       时间单位（不可为null）
     * @param errorValue 超时或异常时返回的预设值
     * @param supplier   异步任务逻辑（不可为null）
     * @return {@link CompletableFuture} 任务结果或超时返回errorValue
     */
    public <T> CompletableFuture<T> supplyAsync(long timeout, TimeUnit unit, T errorValue, Supplier<T> supplier) {
        CompletableFuture<T> origin = CompletableFuture.supplyAsync(supplier, threadPoolExecutor);
        CompletableFuture<T> timeoutFuture = timeoutAfter(timeout, unit);
        return origin.applyToEither(timeoutFuture, Function.identity())
                .exceptionally(throwable -> {
                    origin.cancel(true);
                    // 超时异常
                    log.error(throwable.getMessage());
                    return errorValue;
                });
    }

    /**
     * 创建超时触发器
     *
     * @param timeout 超时时间（需 > 0）
     * @param unit    时间单位（不可为null）
     * @return {@link CompletableFuture} 在超时后抛出 {@link TimeoutException}
     */
    public <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<T> result = new CompletableFuture<>();
        // timeout 时间后 抛出TimeoutException 类似于sentinel / watcher
        delayer.schedule(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
        return result;
    }

}
