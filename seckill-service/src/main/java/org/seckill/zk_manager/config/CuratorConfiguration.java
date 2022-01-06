package org.seckill.zk_manager.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorConfiguration {
    // 重试次数
    @Value("#{T(java.lang.Integer).parseInt('${zookeeper.retryCount}')}")
    private Integer retryCount;
    // 连接超时时间
    @Value("#{T(java.lang.Integer).parseInt('${zookeeper.elapsedTimeMs}')}")
    private Integer elapsedTimeMs;
    // zk集群
    @Value("$(zookeeper.connectString)")
    private String connectString;
    // 会话超时时间
    @Value("#{T(java.lang.Integer).parseInt('${zookeeper.sessionTimeoutMs}')}")
    private Integer sessionTimeoutMs;
    // 连接超时时间
    @Value("#{T(java.lang.Integer).parseInt('${zookeeper.connectionTimeoutMs}')}")
    private Integer connectionTimeoutMs;

    @Bean
    public CuratorFramework curatorFramework() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs)
                .retryPolicy(new RetryNTimes(retryCount, elapsedTimeMs))
                .build();
        curatorFramework.start();
        return curatorFramework;
    }
}
