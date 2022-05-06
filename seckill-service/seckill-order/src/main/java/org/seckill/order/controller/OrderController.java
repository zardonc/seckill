package org.seckill.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    // org.springframework.cloud.client提供通用的客户端
    @Autowired
    private DiscoveryClient discoveryClient;// 服务发现客户端
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/getService")
    public String getService(String name) {
        ServiceInstance instance = null;
        // 获取一个`provider`服务实例
        List<ServiceInstance> instanceList = discoveryClient.getInstances("seckill-product");
        if (instanceList.size() > 0) {
            // 负载均衡客户端获取provider实例
            instance = loadBalancerClient.choose("seckill-product");
        }
        if (instance == null) {
            throw new IllegalStateException("获取不到实例");
        }
        System.out.println("target uri " + instance.getUri());
        String targetUrl = instance.getUri() + "/api/goods/echo?name=" + name;
        String response = restTemplate.getForObject(targetUrl, String.class);
        // 返回结果
        return "consumer:" + response;
    }

    @PostMapping("/add")
    public Long add() {
        return System.currentTimeMillis() / 1000;
    }
}


