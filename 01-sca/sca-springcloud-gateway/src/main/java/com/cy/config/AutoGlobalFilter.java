package com.cy.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author LLH
 * @date 2021/6/7
 */
//@Component
public class AutoGlobalFilter implements GlobalFilter, Ordered {
    /**
     * 处理请求，对请求进行过滤
     * @param exchange 此对应主要用于获取请求对象并进行请求处理
     * @param chain 过滤链对象，通过此对象将请求传递给过滤链中的下一个过滤器
     * @return 数据流对象(通过此对象可以异步响应结果)
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request= exchange.getRequest();
        ServerHttpResponse response=exchange.getResponse();
        //将来这个信息可以来自redis数据库(缓存数据库)
        String token=request.getQueryParams().getFirst("token");
        if(!("admin").equals(token)){
            System.out.println("认证失败");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();//Mono<Void>
        }
        return chain.filter(exchange);//认证ok,继续执行其它的过滤器
    }
    /**
     * 定义过滤器的优先级
     * @return
     */
    @Override
    public int getOrder() {
        return -1;//数值约小优先级约高
    }
}
