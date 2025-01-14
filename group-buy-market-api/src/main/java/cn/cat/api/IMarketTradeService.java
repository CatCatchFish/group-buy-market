package cn.cat.api;

import cn.cat.api.dto.LockMarketPayOrderRequestDTO;
import cn.cat.api.dto.LockMarketPayOrderResponseDTO;
import cn.cat.api.response.Response;

/**
 * 营销交易服务接口
 */
public interface IMarketTradeService {

    Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO);

}
