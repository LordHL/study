package cn.ktl.lab.springmvc.service;


import cn.ktl.lab.springmvc.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static cn.ktl.lab.springmvc.exception.UmErrorCodeEnum.UM_VPN_FRAUD_NOT_ALLOW_LOGIN;


/**
 * @Author lin ho
 * Des: TODO
 */
@Service
public class AllFraudDetectionService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void detectAndHandleVpn(VpnFraudDetectionEvent event) {
        System.out.println("====> 123");
        if (true) {
            throw new BusinessException(UM_VPN_FRAUD_NOT_ALLOW_LOGIN.getCode(), "error");
        }
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void otherFraudDetection(VpnFraudDetectionEvent event) {
//        AsyncDetectionBO vpnDetectionBO = BeanConvertUtils.baseConvert(event, AsyncDetectionBO.class);
//        fraudDetectionService.asyncDetect(vpnDetectionBO);
//    }
}
