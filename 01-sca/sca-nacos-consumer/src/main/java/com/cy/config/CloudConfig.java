package com.cy.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author LLH
 * @date 2021/6/2
 */
@Configuration
public class CloudConfig {

    /**
     * RestTemplate封装了http请求方式，我们可以直接通过
     * 此对象远程访问另一个http服务，这个对象在springboot工程启动时并没有初始化
     * 所以需要我们手动创建一个这样的对象，并将此对象交给spring管理(@Bean)
     * @Bean 应用说明：
     * 1)用于在@configuration注解描述的类中描述方法
     * 2)此注解描述的方法用于创建一个第三方对象并交给spring管理
     * 3)spring存储时默认为bean起的名字为方法名
     */
    @Bean
    //注解描述RestTemplate对象，让RestTemplate对象具备了负载均衡的特性,底层就是loadBalancerClient
    //@LoadBalanced//开启负载均衡的功能,才能基于服务名(eg:nacos-provider)去访问
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced//开启负载均衡的功能,才能基于服务名(eg:nacos-provider)去访问
    public RestTemplate loadBalancedRestTemplate(){
        return new RestTemplate();
    }
}
