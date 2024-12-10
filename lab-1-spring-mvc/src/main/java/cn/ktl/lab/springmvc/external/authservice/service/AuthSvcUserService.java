package cn.ktl.lab.springmvc.external.authservice.service;


import cn.ktl.lab.springmvc.external.authservice.model.RegisterResponseResult;
import cn.ktl.lab.springmvc.external.authservice.model.vo.AuthSvcUserVO;
import cn.ktl.lab.springmvc.external.authservice.model.*;
import cn.ktl.lab.springmvc.external.authservice.model.dto.*;
import jakarta.validation.Valid;

import java.util.List;

public interface AuthSvcUserService {

    /**
     * 创建新用户
     *
     * @param authSvcUserDTO
     */
    RegisterResponseResult<AuthSvcUserVO> createNewUser(AuthSvcUserDTO authSvcUserDTO);


    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    LoginResponseResult login(String username, String password);


    /**
     * 发送 email otp code
     * @param validationOtpDTO email
     * @return T|F
     */
    Boolean generateCredentialValidationOtp(GenerateValidationOtpDTO validationOtpDTO);

    /**
     * 验证 otp code
     * @param validationOtpDTO validationOtpDTO
     * @return T|F
     */
    Boolean validateGenerateOtp(ValidationOtpDTO validationOtpDTO);


    /**
     * 二次验证 totp
     * @param validationTotpDTO validationTotpDTO
     * @return T|F
     */
    LoginResponseResult validateTotp(ValidationTotpDTO validationTotpDTO);

    /**
     * 通过recoveryKey生成新的qrImage
     * @param recoveryTotpDTO recoveryTotpDTO
     * @return qr
     */
    RegisterResponseResult recoveryKeyTotp(RecoveryTotpDTO recoveryTotpDTO);

    /**
     * 获取公钥
     * @return public key
     */
    PublicKeyResponseResult getPublicKey();

    /**
     * 获取 saml sso 登录 断言结果，html 字符串，需要前端渲染
     *
     * @param receiveTokenDTO receiveTokenDTO
     * @return Html String
     */
    String samlReceiveToken(ReceiveTokenDTO receiveTokenDTO);


    /**
     * 修改密码
     * @param authChangePwdDTO authChangePwdDTO
     * @return T|F
     */
    Boolean changeUserPassword(AuthChangePwdDTO authChangePwdDTO);


    /**
     * 通过refreshToken 刷新accessToken
     * @param refreshAccessTokenDTO refreshAccessTokenDTO
     * @return LoginResponseResult
     */
    LoginResponseResult refreshAccessToken(RefreshAccessTokenDTO refreshAccessTokenDTO);

    /**
     * azure sso token 置换 auth token
     * @param socialLoginDTO SocialLoginDTO
     * @return SocialLoginResponseResult
     */
    SocialLoginResponseResult socialLogin( SocialLoginDTO socialLoginDTO);

    /**
     * 更新用户信息
     * @param loginuid loginuid
     * @param authUpdateUserDTO AuthUpdateUserDTO
     * @return T|F
     */
    Boolean updateUser(String loginuid,AuthUpdateUserDTO authUpdateUserDTO);

    void deleteUser(AuthDeleteUserDTO authDeleteUserDTO);


    List<UserListResponseResult> listUser();
}
