package cn.ktl.lab.springmvc.external.authservice.mapper;

import cn.ktl.lab.springmvc.external.authservice.interceptor.XApiKeyInterceptor;
import cn.ktl.lab.springmvc.external.authservice.model.*;
import cn.ktl.lab.springmvc.external.authservice.model.dto.*;
import cn.ktl.lab.springmvc.external.authservice.model.vo.AuthSvcUserVO;
import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.Intercept;

import retrofit2.Response;
import retrofit2.http.*;

@RetrofitClient(baseUrl = "${auth.service.baseUrl}")
@Intercept(handler = XApiKeyInterceptor.class)
public interface AuthSvcUserClient {

    /**
     * 创建新用户
     */
    @POST("v1/create-new-user/")
    RegisterResponseResult<AuthSvcUserVO> createNewUser(@Body AuthSvcUserDTO userDTO);


    /**
     * 登录
     */
    @POST("v1/login/")
    LoginResponseResult login(@Body AuthSvcUserLoginDTO userLoginDTO);


    /**
     * 基于email 生成otp code
     *
     * @param validationOtpDTO validationOtpDTO
     * @return otp code
     */
    @POST("v1/generate-credential-validation-otp/")
    VerificationCodeResponseResult generateCredentialValidationOtp(@Body GenerateValidationOtpDTO validationOtpDTO);

    /**
     * 验证 otp code
     *
     * @param validationOtpDTO validationOtpDTO
     * @return success|error
     */
    @POST("v1/validate-general-otp/")
    AuthSvcResponseResult validateGeneralOtp(@Body ValidationOtpDTO validationOtpDTO);



    /**
     * TOTP 二次验证登录
     *
     * @param validationTotpDTO validationTotpDTO
     * @return accessToken
     */
    @POST("v1/validate-totp/")
    LoginResponseResult validateTotp(@Body ValidationTotpDTO validationTotpDTO);

    /**
     * 通过recoveryKey生成新的qrImage
     *
     * @param recoveryTotpDTO recoveryTotpDTO
     * @return qr
     */
    @POST("v1/validate-totp-recovery-key/")
    RegisterResponseResult recoveryKeyTotp(@Body RecoveryTotpDTO recoveryTotpDTO);


    /**
     * 获取token的公钥
     *
     * @return publicKey
     */
    @GET("v1/get-public-key/")
    PublicKeyResponseResult getPublicKey();


    @POST("v1/saml/receive-token/")
    Response<String> samlReceiveToken(@Body ReceiveTokenDTO receiveTokenDTO);

    /**
     * 修改密码
     *
     * @param authChangePwdDTO authChangePwdDTO
     * @return T|F
     */
    @PUT("v1/change-user-password/")
    AuthSvcResponseResult changeUserPassword(@Body AuthChangePwdDTO authChangePwdDTO);


    /**
     * 通过refreshToken 刷新accessToken
     *
     * @param refreshAccessTokenDTO refreshAccessTokenDTO
     * @return LoginResponseResult
     */
    @POST("v1/access-token-refresh/")
    LoginResponseResult refreshAccessToken(@Body RefreshAccessTokenDTO refreshAccessTokenDTO);


    /**
     * azure sso token 置换 auth token
     *
     * @param socialLoginDTO SocialLoginDTO
     * @return SocialLoginResponseResult
     */
    @POST("v1/social-login/")
    SocialLoginResponseResult socialLogin(@Body SocialLoginDTO socialLoginDTO);

    /**
     * 更新用户信息
     * @param loginuid loginuid
     * @param authUpdateUserDTO AuthUpdateUserDTO
     * @return AuthSvcResponseResult
     */
    @PUT("v1/update-user/{loginuid}/")
    AuthSvcResponseResult updateUser(@Path("loginuid") String loginuid, @Body AuthUpdateUserDTO authUpdateUserDTO);
}
