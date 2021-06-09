package com.cy.controller;

import com.cy.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author LLH
 * @date 2021/5/31
 */
@RestController
@RequestMapping("/consumer/template/")
public class RestConsumerController {
    @Autowired
    private RestTemplate loadBalancedRestTemplate;
    @Autowired
    private ConsumerHttpApi consumerHttpApi;
    @Autowired
    private ConsumerService consumerService;

    @DeleteMapping("{id}")
    public String doDelete(@PathVariable Integer id){
        String url = String.format("http://%s/provider/template/%s","nacos-provider",id);
        loadBalancedRestTemplate.delete(url);
        return "delete ok";
    }

    @PostMapping
    public Map<String,Object> doPost(@RequestBody Map<String,Object> map){
        String url = String.format("http://%s/provider/template/","nacos-provider");
        return loadBalancedRestTemplate.postForObject(url,map,Map.class);
    }

    @PutMapping
    public String doPut(@RequestBody Map<String,Object> map){
        String url = String.format("http://%s/provider/template/","nacos-provider");
        loadBalancedRestTemplate.put(url, map);
        return "update ok";
    }

    @GetMapping("echo/{msg}")
    public String doEcho(@PathVariable String msg){
        System.out.println(consumerService.doGetResource());
        return consumerHttpApi.echoMsg(msg);
    }
}
