package com.cy.service;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**构建请求参数解析对象，基于此对象的返回值进行授权访问设计
 * @author LLH
 * @date 2021/6/2
 */
@Component
public class DefaultRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        //基于请求参数值进行授权规则设计
        //获取请求参数(http://localhost:8090/consumer/findById/2?origin=app1)
        String origin = httpServletRequest.getParameter("origin");
        return origin;//将值返回给了方法的调用方，回调函数，只做方法的实现，谁调用返回给谁
    }
}
