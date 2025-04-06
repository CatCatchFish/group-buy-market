package cn.cat.test;

import cn.cat.infrastructure.event.EventPublisher;
import cn.cat.types.util.FutureUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.*;
import java.util.function.Function;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private EventPublisher publisher;
    @Value("${spring.rabbitmq.config.producer.topic_team_success.routing_key}")
    private String routingKey;

    @Test
    public void test_rabbitmq() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        publisher.publish(routingKey, "订单结算：ORD-20231234");
        publisher.publish(routingKey, "订单结算：ORD-20231235");
        publisher.publish(routingKey, "订单结算：ORD-20231236");
        publisher.publish(routingKey, "订单结算：ORD-20231237");
        publisher.publish(routingKey, "订单结算：ORD-20231238");

        // 等待，消息消费。测试后，可主动关闭。
        countDownLatch.await();
    }

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    @Resource
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    @Resource
    private FutureUtil futureUtil;

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 提交任务到线程池
        CompletableFuture<Void> taskFuture = CompletableFuture.runAsync(() -> {
            try {
                // 模拟耗时任务（需检查中断）
                for (int i = 0; i < 10; i++) {
                    if (Thread.interrupted()) {
                        throw new InterruptedException("任务被中断");
                    }
                    Thread.sleep(1000); // 每次阻塞1秒
                    System.out.println("任务进度: " + (i + 1) + "/10");
                }
                System.out.println("任务正常完成");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }
        }, threadPoolExecutor);

        CompletableFuture<Void> timeoutFuture = new CompletableFuture<>();
        scheduledThreadPoolExecutor.schedule(() -> {
            timeoutFuture.completeExceptionally(new TimeoutException("任务超时"));
        }, 2, TimeUnit.SECONDS);

        // 组合任务与超时触发器
        CompletableFuture<Void> result = taskFuture.applyToEither(timeoutFuture, Function.identity())
                .exceptionally(throwable -> {
                    taskFuture.cancel(true); // 超时时强制中断任务
                    log.error("任务执行异常", throwable);
                    return null;
                })
                .whenComplete((re, ex) -> {
                    System.out.println("任务执行完成");
                });

        try {
            result.get();
        } catch (ExecutionException e) {
            log.error("任务执行异常", e);
        }
        countDownLatch.await();
    }

    @Test
    public void futureUtilTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CompletableFuture<String> completableFuture = futureUtil.supplyAsync(3, TimeUnit.SECONDS, "errorValue", () -> {
            try {
                // 模拟耗时任务（需检查中断）
                for (int i = 0; i < 10; i++) {
                    if (Thread.interrupted()) {
                        throw new InterruptedException("任务被中断");
                    }
                    Thread.sleep(1000); // 每次阻塞1秒
                    System.out.println("任务进度: " + (i + 1) + "/10");
                }
                System.out.println("任务正常完成");
                return "task1 result";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
                return null;
            }
        });

        completableFuture.thenApply(result -> {
            System.out.println("任务执行完成，结果：" + result);
            return result;
        });

        countDownLatch.await();
    }

}
