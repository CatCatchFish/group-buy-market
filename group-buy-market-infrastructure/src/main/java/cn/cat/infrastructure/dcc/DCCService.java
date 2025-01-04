package cn.cat.infrastructure.dcc;

import cn.cat.types.annotations.DCCValue;
import org.springframework.stereotype.Service;

@Service
public class DCCService {

    /**
     * 降级开关 0关闭、1开启
     */
    @DCCValue("downgradeSwitch:0")
    private String downgradeSwitch;

    @DCCValue("cutRange:100")
    private String cutRange;

    public boolean isDowngradeSwitch() {
        return "1".equals(downgradeSwitch);
    }

    public boolean isCutRange(String userId) {
        int hashCode = Math.abs(userId.hashCode());

        int lastTwoDigits = hashCode % 100;
        return Integer.parseInt(cutRange) > lastTwoDigits;
    }

}
