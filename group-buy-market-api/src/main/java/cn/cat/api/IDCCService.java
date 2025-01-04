package cn.cat.api;

import cn.cat.api.response.Response;

public interface IDCCService {

    Response<Boolean> updateConfig(String key, String value);

}
