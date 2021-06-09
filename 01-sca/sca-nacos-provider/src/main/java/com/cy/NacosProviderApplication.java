package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LLH
 * @date 2021/5/28
 */
@SpringBootApplication
//启动服务时，是启动了一个独立的进程
public class NacosProviderApplication {
    /**
     * 这个服务启动时，会自动向nacos发出一个http请求（发送请求的代码在
     * spring-cloud-starter-alibaba-nacos-discovery这个依赖中），然后
     * 将这个服务注册到nacos服务端，nacos服务端
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApplication.class, args);
    }
}
