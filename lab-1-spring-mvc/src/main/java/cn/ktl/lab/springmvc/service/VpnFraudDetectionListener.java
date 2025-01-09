package cn.ktl.lab.springmvc.service;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author lin ho
 * Des: VpnFraudDetectionListener
 */

@Component
@Slf4j
public class VpnFraudDetectionListener implements ApplicationListener<VpnFraudDetectionEvent> {

    private final AllFraudDetectionService allFraudDetectionService;

    public VpnFraudDetectionListener(AllFraudDetectionService allFraudDetectionService) {
        this.allFraudDetectionService = allFraudDetectionService;
    }

    @Value("${um.fraud.detect.enable: false}")
    private boolean fraudDetectEnable;

    @Override
    public void onApplicationEvent(@NotNull VpnFraudDetectionEvent event) {
        if (fraudDetectEnable) {
            log.info("VpnFraudDetectionListener user :{} fraud detect!", event.getUserId());
            allFraudDetectionService.detectAndHandleVpn(event);
        }
    }
}
