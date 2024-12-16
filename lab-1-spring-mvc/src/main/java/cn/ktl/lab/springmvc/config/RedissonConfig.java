package cn.ktl.lab.springmvc.config;


import cn.hutool.core.collection.CollUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZBY
 * @description
 * @createDate 2024-07-24 10:02
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.password}")
    private String password;

    @Value("${spring.data.redis.port}")
    private Integer port;

    @Value("${spring.data.redis.redisson.singleServerConfig.idleConnectionTimeout}")
    private Integer idleConnectionTimeout;

    @Value("${spring.data.redis.redisson.singleServerConfig.connectTimeout}")
    private Integer connectTimeout;

    @Value("${spring.data.redis.redisson.singleServerConfig.timeout}")
    private Integer timeout;

    @Value("${spring.data.redis.redisson.singleServerConfig.retryAttempts}")
    private Integer retryAttempts;

    @Value("${spring.data.redis.redisson.singleServerConfig.retryInterval}")
    private Integer retryInterval;

    @Value("${spring.data.redis.redisson.singleServerConfig.subscriptionsPerConnection}")
    private Integer subscriptionsPerConnection;

    @Value("${spring.data.redis.redisson.singleServerConfig.subscriptionConnectionMinimumIdleSize}")
    private Integer subscriptionConnectionMinimumIdleSize;

    @Value("${spring.data.redis.redisson.singleServerConfig.subscriptionConnectionPoolSize}")
    private Integer subscriptionConnectionPoolSize;

    @Value("${spring.data.redis.redisson.singleServerConfig.connectionMinimumIdleSize}")
    private Integer connectionMinimumIdleSize;

    @Value("${spring.data.redis.redisson.singleServerConfig.subscriptionConnectionPoolSize}")
    private Integer connectionPoolSize;

    @Value("${spring.data.redis.redisson.singleServerConfig.dnsMonitoringInterval}")
    private Integer dnsMonitoringInterval;

    @Value("${spring.data.redis.redisson.singleServerConfig.sslEnableEndpointIdentification:true}")
    private Boolean sslEnableEndpointIdentification;

    @Value("${spring.data.redis.database}")
    private Integer database;

    //集群模式
//    @Value("${spring.data.redis.redisson.cluster.nodes}")
    private List<String> clusterNodes;

    //哨兵模式 - 主节点
//    @Value("${spring.data.redis.redisson.sentinel.master}")
    private String master;
    //    //哨兵模式 - 哨兵节点
//    @Value("${spring.data.redis.redisson.sentinel.nodes}")
    private List<String> sentinelNodes;

    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        //单节点的配置
        configSingleServer(config);
        //集群模式配置
        //configCluster(config);
        //哨兵模式配置
//        configSentinel(config);
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }

    /**
     * 单节点模式配置
     *
     * @param config redisson配置
     */
    private void configSingleServer(Config config) {
        String redisUrl;
        if (sslEnableEndpointIdentification){
            redisUrl = "rediss://" + redisHost.split(":")[0] + ":" + port;
        }else {
            redisUrl = "redis://" + redisHost.split(":")[0] + ":" + port;
        }
        config.useSingleServer()
                .setAddress(redisUrl)
                .setPassword(password)
                .setIdleConnectionTimeout(idleConnectionTimeout)
                .setConnectTimeout(connectTimeout)
                .setTimeout(timeout)
                .setDatabase(database)
                .setRetryAttempts(retryAttempts)
                .setRetryInterval(retryInterval)
                .setSubscriptionsPerConnection(subscriptionsPerConnection)
                .setSubscriptionConnectionMinimumIdleSize(subscriptionConnectionMinimumIdleSize)
                .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                .setConnectionPoolSize(connectionPoolSize)
                .setDnsMonitoringInterval(dnsMonitoringInterval)
                .setSslEnableEndpointIdentification(sslEnableEndpointIdentification)
        ;
    }


    /**
     * 哨兵模式配置
     *
     * @param config redisson配置
     */
    private void configSentinel(Config config) {
        config.useSentinelServers()
                .addSentinelAddress(packageNode(sentinelNodes))
                .setMasterName(master)
                .setPassword(password)
                .setIdleConnectionTimeout(idleConnectionTimeout)
                .setConnectTimeout(connectTimeout)
                .setTimeout(timeout)
                .setRetryAttempts(retryAttempts)
                .setRetryInterval(retryInterval)
                .setSubscriptionsPerConnection(subscriptionsPerConnection)
                .setSubscriptionConnectionMinimumIdleSize(subscriptionConnectionMinimumIdleSize)
                .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
                .setDnsMonitoringInterval(dnsMonitoringInterval)
                .setPassword(password);
    }

    /**
     * 集群模式配置
     *
     * @param config redisson配置
     */
    private void configCluster(Config config) {
        config.useClusterServers()
                .addNodeAddress(packageNode(clusterNodes))
                .setPassword(password)
                .setIdleConnectionTimeout(idleConnectionTimeout)
                .setConnectTimeout(connectTimeout)
                .setTimeout(timeout)
                .setRetryAttempts(retryAttempts)
                .setRetryInterval(retryInterval)
                .setSubscriptionsPerConnection(subscriptionsPerConnection)
                .setSubscriptionConnectionMinimumIdleSize(subscriptionConnectionMinimumIdleSize)
                .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
                .setDnsMonitoringInterval(dnsMonitoringInterval)
                .setPassword(password);
    }

    /**
     * @param originalNodes 配置文件中的node
     * @return 添加Redis前缀的node的数组
     */
    private String[] packageNode(List<String> originalNodes) {
        List<String> clusterNodes = new ArrayList<>();
        if (CollUtil.isEmpty(originalNodes)) {
            return null;
        }
        for (String sentinelNode : originalNodes) {
            clusterNodes.add("redis://" + sentinelNode);
        }
        return clusterNodes.toArray(new String[0]);
    }
}