package cn.cat.infrastructure.dao.po.base;

import lombok.Getter;

@Getter
public class Page {

    private Integer count;

    public void setCount(Integer count) {
        this.count = count;
    }

}
