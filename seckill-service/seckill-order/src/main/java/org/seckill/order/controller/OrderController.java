package org.seckill.order.controller;

import org.seckill.feign.IProductClient;
import org.seckill.order.service.TaskPoolManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Queue;
import java.util.UUID;

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
    @Autowired
    private TaskPoolManager taskPoolManager;
    @Autowired
    private IProductClient iProductClient;

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

    /**
     * 功能描述: 模拟下单请求 入口
     * 〈〉
     * @Param: [id]
     * @Return: java.lang.String
     * @Author: lang
     */
    @GetMapping("/start/{id}")
    public String start(@PathVariable Long id) {
        //模拟的随机数
        String orderNo = System.currentTimeMillis() + UUID.randomUUID().toString();
        taskPoolManager.addOrders(orderNo);
        return "Test ThreadPoolExecutor start";
    }

    /**
     * 停止服务
     * @param id
     * @return
     */
    @GetMapping("/end/{id}")
    public String end(@PathVariable Long id) {
        taskPoolManager.shutdown();
        Queue q = taskPoolManager.getMsgQueue();
        System.out.println("关闭了线程服务，还有未处理的信息条数：" + q.size());
        return "Test ThreadPoolExecutor start";
    }

    @GetMapping("/hello02")
    public String feignTest(String name) {
        // 使用 Feign 调用接口
        String response = iProductClient.echo(name);
        // 返回结果
        return "consumer: " + response;
    }
}


