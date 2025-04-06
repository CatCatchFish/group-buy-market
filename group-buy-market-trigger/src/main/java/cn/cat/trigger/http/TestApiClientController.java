package cn.cat.trigger.http;

import cn.cat.api.dto.NotifyRequestDTO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CountDownLatch;

/**
 * @description 回调服务接口测试
 */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/test/")
public class TestApiClientController {

    // http://127.0.0.1:8091/api/v1/test/group_buy_notify

    /**
     * 模拟回调案例
     *
     * @param notifyRequestDTO 通知回调参数
     * @return success 成功，error 失败
     */
    @RequestMapping(value = "group_buy_notify", method = RequestMethod.POST)
    public String groupBuyNotify(@RequestBody NotifyRequestDTO notifyRequestDTO) {
        log.info("模拟测试第三方服务接收拼团回调 {}", JSON.toJSONString(notifyRequestDTO));
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            Thread.sleep(4000);
            countDownLatch.countDown();

            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("模拟测试第三方服务接收拼团回调异常 {}", e.getMessage());
            return "error";
        }
        return "success";
    }

}
