package cn.cat.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum NotifyTaskHTTPEnumVO {

    SUCCESS("success", "成功"),
    ERROR("error", "失败"),
    TIME_OUT("time_out", "超时"),
    NULL(null, "空执行"),
    ;

    private String code;
    private String info;

}
