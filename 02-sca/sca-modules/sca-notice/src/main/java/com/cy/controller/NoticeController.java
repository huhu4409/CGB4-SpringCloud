package com.cy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author LLH
 * @date 2021/6/8
 */
@RestController
@RequestMapping("/notice/")
public class NoticeController {

    @PostMapping("sms/send")
    public String doSendCodeMsg(@RequestBody Map<String,Object> map){
        System.out.println("已发送短信消息:" + map);
        //后续可以在此位置调用短信平台发短信
        return "send ok";
    }

    @PostMapping("/log/record")
    public String doLogInfo(@RequestBody Map<String,Object> log){
        System.out.println("notice.log:" + log);
        return "log ok";
    }
}
