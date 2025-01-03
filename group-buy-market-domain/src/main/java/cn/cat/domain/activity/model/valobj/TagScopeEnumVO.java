package cn.cat.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TagScopeEnumVO {

    VISIBLE(true, false, "是否可看见拼团"),
    ENABLE(true, false, "是否可参与拼团"),
    ;

    private Boolean allow;
    private Boolean refuse;
    private String desc;

}
