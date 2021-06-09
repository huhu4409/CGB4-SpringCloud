package com.cy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**@RefreshScope 描述Controller类时，假如配置中心的配置发生变化
 * 我们在对此类中的url资源会进行访问时，会重新构建此对象
 * @author LLH
 * @date 2021/6/3
 */
@RefreshScope //支持配置动态刷新
@RestController
public class NacosConfigController {//此对象只创建一次
    private static Logger logger = LoggerFactory.getLogger(NacosConfigController.class);
    @Value("${logging.level.com.cy:info}")//这里的error表示没有取到日志级别的默认设置
    private String logLevel;
    @Value("${server.tomcat.threads.max:200}")
    private String serverThreadMax;
    @Value("${page.pageSize:20}")
    private Integer pageSize;

    @GetMapping("/config/log/level")
    public String doGetLoggingLevel(){
        System.out.println("===level.info===" + logLevel);
        logger.info("==level.info==");
        logger.error("==level.error==");
        logger.debug("==level.debug==");
        return "log level is " + logLevel;
    }

    @GetMapping("/config/threadMax")
    public String doGetServerThreadMax(){
        return "server.threads.max is " + serverThreadMax;
    }

    @GetMapping("/config/pageSize")
    public String doGetPageSize(){
        return "page.pageSize is " + pageSize;
    }
}
