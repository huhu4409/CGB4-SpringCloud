package com.cy.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author LLH
 * @date 2021/6/2
 */
@Service
public class ConsumerService {
    /**
     * SentinelResource注解描述的方法，
     * 可以通过指定链路进行流量统计并执行限流操作
     * @return
     */
    @SentinelResource("doGetResource")
    public String doGetResource(){
        return "do get resource";
    }
}
