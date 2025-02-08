package cn.cat.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotifyRequestDTO {

    private String teamId;
    private List<String> outTradeNoList;

}
