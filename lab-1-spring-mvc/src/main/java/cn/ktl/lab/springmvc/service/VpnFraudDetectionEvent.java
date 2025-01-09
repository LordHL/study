package cn.ktl.lab.springmvc.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author lin ho
 * Des: FraudDetectionEvent
 */
@Setter
@Getter
public class VpnFraudDetectionEvent extends ApplicationEvent {


    private Long userId;

    private Integer checkPoint;

    private String ip;

    public VpnFraudDetectionEvent(Object source, Long userId, Integer checkPoint, String ip) {
        super(source);
        this.userId = userId;
        this.checkPoint = checkPoint;
        this.ip = ip;
    }
}
