package cn.cat.api;

import cn.cat.api.dto.LockMarketPayOrderRequestDTO;
import cn.cat.api.dto.LockMarketPayOrderResponseDTO;
import cn.cat.api.dto.SettlementMarketPayOrderRequestDTO;
import cn.cat.api.dto.SettlementMarketPayOrderResponseDTO;
import cn.cat.api.response.Response;

/**
 * 营销交易服务接口
 */
public interface IMarketTradeService {

    Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO);

    Response<SettlementMarketPayOrderResponseDTO> settlementMarketPayOrder(SettlementMarketPayOrderRequestDTO requestDTO);

}
