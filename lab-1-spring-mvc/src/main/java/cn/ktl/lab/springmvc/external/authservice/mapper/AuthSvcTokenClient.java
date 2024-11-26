package cn.ktl.lab.springmvc.external.authservice.mapper;

import cn.ktl.lab.springmvc.external.authservice.model.ApiKeyResponseResult;
import cn.ktl.lab.springmvc.external.authservice.model.dto.AuthSvcApiKeyDTO;
import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;

import retrofit2.http.Body;
import retrofit2.http.POST;

@RetrofitClient(baseUrl = "${auth.service.baseUrl}")
public interface AuthSvcTokenClient {

    @POST("v1/generate-api-key/")
    public ApiKeyResponseResult generateApiKey(@Body AuthSvcApiKeyDTO apiKeyDTO);

}
