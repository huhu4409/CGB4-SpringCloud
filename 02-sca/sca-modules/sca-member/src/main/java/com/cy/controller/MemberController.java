package com.cy.controller;

import com.cy.domain.LoginVo;
import com.cy.domain.Member;
import com.cy.sca.api.annotation.RequiredLog;
import com.cy.sca.api.feign.RemoteNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LLH
 * @date 2021/6/8
 */
@RestController
@RequestMapping("/member/")
public class MemberController {
    /**
     *
     */
    @Value("${server.port}")
    private String port;
    /**
     * 基于此接口中的方法进行远程通知服务(sms,email)的调用
     */
    @Autowired
    private RemoteNoticeService remoteNoticeService;

    /**
     * 404 资源没找到(访问的url是否正确)
     * 400 客户端发送的请求参数与服务端可接收参数不匹配(个数，格式，类型)
     * 405 请求方式不匹配(服务端定义的是post请求，客户端访问使用get请求)
     * 415 请求数据的类型(Content-Type)与服务端不匹配
     * 401 认证失败(先登录)
     * 403 授权失败
     * ....
     * @param member
     * @return
     */
    @PostMapping("register")
    public String doRegister(@RequestBody Member member){
        System.out.println(port + " to do register " +member);
        Map<String,Object> map = new HashMap<>();
        map.put("mobile", "1041231321");//手机号
        map.put("code", "3545");//验证码
        //基于feign方式调用远程服务
        remoteNoticeService.sendMsg(map);
        return "注册成功";
    }

    /**
     * 此业务执行时要记录登录日志？
     * 1)如何标记哪个方法记录日志(基于注解方式进行标记)
     * 2)通过谁获取标记以及基于什么方式获取日志(AOP方式)
     * 3)将获取到的日志交给哪个服务进行记录(写文件，写数据库，发送给大数据分析系统)
     * @param loginVo
     * @return
     */
    @RequiredLog//通过自定义注解的方式去对此方法进行描述，在执行此方式时记录其登录的日志（AOP）
    @PostMapping("doLogin")
    public String doLogin(@RequestBody LoginVo loginVo){
        System.out.println(loginVo);
        return "登录成功";
    }
}
