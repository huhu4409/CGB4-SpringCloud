package com.cy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LLH
 * @date 2021/6/3
 */
@SpringBootApplication
public class NacosConfigApplication {
    private static Logger logger = LoggerFactory.getLogger(NacosConfigApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
        logger.info("===info===");
        logger.error("===error===");
        logger.debug("===debug===");
    }
}
