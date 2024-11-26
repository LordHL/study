package cn.ktl.lab.springmvc.config;

/**
 * @Author lin ho
 * Des: SnowflakeIdConfig
 */

import cn.ktl.lab.springmvc.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeIdConfig {

    @Value("${um.snowflake.worker-id:16}")
    private long workerId;

    @Value("${um.snowflake.datacenter-id:16}")
    private long datacenterId;

    @Bean
    public SnowflakeIdUtils snowflakeIdUtils() {
        return new SnowflakeIdUtils(workerId, datacenterId);
    }
}

