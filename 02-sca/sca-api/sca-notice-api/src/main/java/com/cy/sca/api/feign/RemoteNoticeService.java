package com.cy.sca.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author LLH
 * @date 2021/6/9
 */
@FeignClient(value="sca-notice",contextId = "remoteNoticeService")
public interface RemoteNoticeService {
    //访问sca-notice服务下的/notice/sms/send资源
    @PostMapping("/notice/sms/send")
    void sendMsg(@RequestBody Map<String,Object> msg);//code可以简单理解为验证码

    @PostMapping("/notice/log/record")
    void doLoginfo(Map<String,Object> log);
}
