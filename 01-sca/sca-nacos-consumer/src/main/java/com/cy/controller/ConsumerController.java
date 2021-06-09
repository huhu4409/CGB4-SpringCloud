package com.cy.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author LLH
 * @date 2021/5/28
 */
@RestController
public class ConsumerController {
    @Value("${spring.application.name}")
    private String appName;

    /**
     * 此对象可以基于服务名，找到具体的服务实例，找到了实例后
     * 就可以获取实例对应的ip地址和端口号
     * 通过ip地址和端口号就可以通过restTemplate调用对应的服务
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ConsumerService consumerService;
    //线程安全，数字自增
    private AtomicLong atomicLong = new AtomicLong(0);

    @GetMapping("/consumer/doRestEcho")
    public String doRestEcho(){
        //模拟耗时操作（请求相应时间让他长一点，模拟慢调用）
        //Thread.sleep(200);
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        String url = String.format("http://%s:%s/provider/echo/%s",serviceInstance.getHost(),serviceInstance.getPort(),appName);
        System.out.println("request url:" + url);
        //System.out.println(consumerService.doGetResource());
        System.out.println(atomicLong.incrementAndGet());//先递增再返回具体结果
        //向服务提供方发起请求
        return restTemplate.getForObject(
                url,//要请求的服务的地址
                String.class);//
    }

    @GetMapping("/consumer/findById/{id}")
    @SentinelResource("doFindById")
    public String doFindById(@PathVariable Integer id){
        return "hot is id : " + id;
    }
}
