package com.cy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author LLH
 * @date 2021/5/28
 */
@RestController
public class ProviderController {
    @Value("${server.port}")
    private String server;

    @GetMapping(value = "/provider/echo/{string}")
    public String doEcho(@PathVariable String string, HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        System.out.println(headerNames);
        return server + " say:Hello Nacos Discovery " + string;
    }
}
