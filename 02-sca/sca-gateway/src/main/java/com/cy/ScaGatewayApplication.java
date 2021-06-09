package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LLH
 * @date 2021/6/8
 */
@EnableDiscoveryClient //让注册中心能够发现，扫描到该服务
@SpringBootApplication
public class ScaGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScaGatewayApplication.class, args);
    }
}
