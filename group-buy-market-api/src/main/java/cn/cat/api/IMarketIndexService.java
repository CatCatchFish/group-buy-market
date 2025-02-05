package cn.cat.api;

import cn.cat.api.dto.GoodsMarketRequestDTO;
import cn.cat.api.dto.GoodsMarketResponseDTO;
import cn.cat.api.response.Response;

public interface IMarketIndexService {

    /**
     * 查询拼团营销配置
     *
     * @param goodsMarketRequestDTO 营销商品信息
     * @return 营销配置信息
     */
    Response<GoodsMarketResponseDTO> queryGroupBuyMarketConfig(GoodsMarketRequestDTO goodsMarketRequestDTO);

}
