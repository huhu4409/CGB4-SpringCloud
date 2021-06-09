package com.cy.service;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于此类处理限流、熔断事件触发时出现的异常
 * 1)系统有默认处理限流、熔断事件触发的异常的处理器吗？(DefaultBlockExceptionHandler)
 * 2)当默认处理异常的方式不满足业务需求时，我们需要自己定义异常处理器
 * 3)我们自己定义异常处理器时需要实现BlockExceptionHandler接口，
 * 并将异常处理器对象交给spring管理
 * 4)我们自己定义了异常处理器以后，默认的就无效了吗？(是的)，why
 *
 * @author LLH
 * @date 2021/6/2
 */
@Component
public class ServiceBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        //设置相应数据编码
        httpServletResponse.setCharacterEncoding("utf-8");
        //告知客户端(浏览器)你向它输出的是数据类型及编码
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        Map<String,Object> map = new HashMap<>();
        if(e instanceof DegradeException){
            map.put("status",601);
            map.put("message", "服务被熔断了!");
        } else if( e instanceof FlowException){
            map.put("status",602);
            map.put("message", "服务被限流了!");
        }else{
            map.put("status", 429);
            map.put("message","访问过于频繁，请稍后再访问");
        }
        //将map对象转换为json字符串
        //借助jackson中的api（ObjectMapper）中的writeValueAsString方法进行交换
        String jsonstr = new ObjectMapper().writeValueAsString(map);
        writer.println(jsonstr);
        writer.flush();
    }
}
