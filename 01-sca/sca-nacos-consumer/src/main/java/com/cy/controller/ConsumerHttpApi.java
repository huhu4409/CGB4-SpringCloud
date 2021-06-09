package com.cy.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LLH
 * @date 2021/6/1
 */
@FeignClient(name="nacos-provider")
public interface ConsumerHttpApi {
    @GetMapping("/provider/echo/{msg}")
    public String echoMsg(@PathVariable String msg);
}
