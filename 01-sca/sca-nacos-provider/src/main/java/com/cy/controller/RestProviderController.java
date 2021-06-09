package com.cy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author LLH
 * @date 2021/5/31
 */
@RestController
@RequestMapping("/provider/template/")
public class RestProviderController {

    @Value("${server.port}")
    private String server;

    @DeleteMapping("{id}")
    public void doDelete(@PathVariable Integer id){
        System.out.println(id + " server is by " + server);
    }

    @PostMapping
    public Map<String,Object> doPost(@RequestBody Map<String,Object> map){
        System.out.println("consumer post data:" + map);
        map.put("status", 1);
        map.put("server.port", server);
        return map;
    }

    @PutMapping
    public void doPut(@RequestBody Map<String,Object> map){
        System.out.println("consumer put data:" + map);
    }
}
