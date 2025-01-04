package cn.cat.trigger.http;

import cn.cat.api.IDCCService;
import cn.cat.api.response.Response;
import cn.cat.types.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/v1/gbm/dcc/")
public class DCCController implements IDCCService {

    @Resource
    private CuratorFramework client;

    private static final String BASE_CONFIG_PATH = "/group-buy-market-dcc";
    private static final String BASE_CONFIG_PATH_CONFIG = BASE_CONFIG_PATH + "/config";

    @RequestMapping(value = "update_config", method = RequestMethod.GET)
    @Override
    public Response<Boolean> updateConfig(String key, String value) {
        try {
            log.info("DCC 动态配置值变更开始 key:{} value:{}", key, value);
            String keyPath = BASE_CONFIG_PATH_CONFIG.concat("/").concat(key);
            if (null == client.checkExists().forPath(keyPath)) {
                client.create().creatingParentsIfNeeded().forPath(keyPath);
                log.info("DCC 节点监听 base node {} not absent create new done!", keyPath);
            }
            Stat stat = client.setData().forPath(keyPath, value.getBytes(StandardCharsets.UTF_8));
            log.info("DCC 动态配置值变更完成 key:{} value:{} time:{}", key, value, stat.getCtime());
            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .build();
        } catch (Exception e) {
            log.info("DCC 动态配置值变更失败 key:{} value:{}", key, value);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

}
