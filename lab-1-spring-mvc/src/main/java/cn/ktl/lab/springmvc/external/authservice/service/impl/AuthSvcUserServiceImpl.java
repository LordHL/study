package cn.ktl.lab.springmvc.external.authservice.service.impl;


import cn.ktl.lab.springmvc.exception.BusinessException;
import cn.ktl.lab.springmvc.exception.UmErrorCodeEnum;
import cn.ktl.lab.springmvc.external.authservice.mapper.AuthSvcUserClient;
import cn.ktl.lab.springmvc.external.authservice.model.vo.AuthSvcUserVO;
import cn.ktl.lab.springmvc.external.authservice.service.AuthSvcUserService;
import cn.ktl.lab.springmvc.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import cn.ktl.lab.springmvc.external.authservice.model.*;
import cn.ktl.lab.springmvc.external.authservice.model.dto.*;

import java.util.List;

import static cn.ktl.lab.springmvc.exception.UmErrorCodeEnum.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthSvcUserServiceImpl implements AuthSvcUserService {

    private final AuthSvcUserClient authSvcUserClient;

    @Override
    public RegisterResponseResult<AuthSvcUserVO> createNewUser(AuthSvcUserDTO authSvcUserDTO) {
        RegisterResponseResult<AuthSvcUserVO> result = authSvcUserClient.createNewUser(authSvcUserDTO);
        if (!result.isSuccess()) {
            log.error("auth service call error result: {}, param: {}", JsonUtils.toJsonString(result), JsonUtils.toJsonString(authSvcUserDTO));
            throw new BusinessException(UmErrorCodeEnum.UM_USER_AUTH_SVC_CALL_FAILED);
        }
        return result;
    }

    @Override
    public LoginResponseResult login(String username, String password) {
        AuthSvcUserLoginDTO dto = new AuthSvcUserLoginDTO();
        dto.setUsername(username);
        dto.setPassword(password);

        return authSvcUserClient.login(dto);
    }

    @Override
    public Boolean generateCredentialValidationOtp(GenerateValidationOtpDTO validationOtpDTO) {
        VerificationCodeResponseResult verificationCodeResponseResult = authSvcUserClient.generateCredentialValidationOtp(validationOtpDTO);
        return verificationCodeResponseResult.isSuccess();
    }

    @Override
    public Boolean validateGenerateOtp(ValidationOtpDTO validationOtpDTO) {
        AuthSvcResponseResult authSvcResponseResult = authSvcUserClient.validateGeneralOtp(validationOtpDTO);
        return authSvcResponseResult.isSuccess();
    }

    @Override
    public LoginResponseResult validateTotp(ValidationTotpDTO validationTotpDTO) {

        return authSvcUserClient.validateTotp(validationTotpDTO);
    }

    @Override
    public RegisterResponseResult recoveryKeyTotp(RecoveryTotpDTO recoveryTotpDTO) {
        RegisterResponseResult registerResponseResult = authSvcUserClient.recoveryKeyTotp(recoveryTotpDTO);
        if (!registerResponseResult.isSuccess()){
            throw BusinessException.of(UM_INVOKE_AUTH_RECOVERY_KEY_FAILED);
        }
        return registerResponseResult;
    }

    @Override
    public PublicKeyResponseResult getPublicKey() {
        PublicKeyResponseResult result = authSvcUserClient.getPublicKey();
        if (!result.isSuccess()){
            throw BusinessException.of(UM_INVOKE_AUTH_GET_PUBLIC_KEY_FAILED);
        }
        return result;
    }

    @Override
    public String samlReceiveToken(ReceiveTokenDTO receiveTokenDTO) {
        Response<String> response = authSvcUserClient.samlReceiveToken(receiveTokenDTO);
        if (response.isSuccessful() && response.body() != null) {
            return response.body();
        }
        throw new BusinessException(UmErrorCodeEnum.UM_USER_AUTH_SVC_CALL_FAILED);
    }

    @Override
    public Boolean changeUserPassword(AuthChangePwdDTO authChangePwdDTO) {
        AuthSvcResponseResult authSvcResponseResult = authSvcUserClient.changeUserPassword(authChangePwdDTO);
        if (!authSvcResponseResult.isSuccess()){
            throw BusinessException.of(UM_USER_AUTH_CHANGE_PASSWORD_FAILED);
        }
        return Boolean.TRUE;
    }

    @Override
    public LoginResponseResult refreshAccessToken(RefreshAccessTokenDTO refreshAccessTokenDTO) {
        LoginResponseResult responseResult = authSvcUserClient.refreshAccessToken(refreshAccessTokenDTO);
        if (!responseResult.isSuccess()){
            throw BusinessException.of(UM_USER_CALL_AUTH_REFRESH_ACCESS_TOKEN_FAILED);
        }
        return responseResult;
    }

    @Override
    public SocialLoginResponseResult socialLogin(SocialLoginDTO socialLoginDTO) {
        SocialLoginResponseResult responseResult = authSvcUserClient.socialLogin(socialLoginDTO);
        if (!responseResult.isSuccess()){
            throw BusinessException.of(UM_USER_INVOKE_AUTH_SOCIAL_LOGIN_FAILED);
        }
        return responseResult;
    }

    @Override
    public Boolean updateUser(String loginuid, AuthUpdateUserDTO authUpdateUserDTO) {

        AuthSvcResponseResult responseResult = authSvcUserClient.updateUser(loginuid, authUpdateUserDTO);
        if (!responseResult.isSuccess()){
            throw BusinessException.of(UM_USER_INVOKE_AUTH_SOCIAL_LOGIN_FAILED);
        }
        return Boolean.TRUE;
    }

    @Override
    public void deleteUser(AuthDeleteUserDTO authDeleteUserDTO) {
        AuthSvcResponseResult responseResult = authSvcUserClient.deleteUser(authDeleteUserDTO);
        if (!responseResult.isSuccess()){
            throw BusinessException.of(UM_USER_INVOKE_AUTH_SOCIAL_LOGIN_FAILED);
        }
    }

    @Override
    public List<UserListResponseResult> listUser() {
        return authSvcUserClient.listUser();
    }
}
