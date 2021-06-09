package com.cy.sca.api.aspect;

import com.cy.sca.api.feign.RemoteNoticeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LLH
 * @date 2021/6/9
 */
@Aspect//定义切面
@Component
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 基于@Pointcut注解定义切入点
     * 这里表示由RequiredLog注解描述的方法为一个切入点方法
     */
    @Pointcut("@annotation(com.cy.sca.api.annotation.RequiredLog)")
    public void doLog(){}//承载切入点表达式的定义，方法不写任何内容

    @Around("doLog()")
    //ProceedingJoinPoint继承了JoinPoint,暴露出proceed这个方法就是用于启动目标方法执行的
    //环绕通知=前置+目标方法执行+后置通知,就是可以支持around这种切面
    public Object doAround(ProceedingJoinPoint jp) throws Throwable{
        Map<String,Object> map = new HashMap<>();
        //获取目标类的类全名
        String targetClassName = jp.getTarget().getClass().getName();
        //System.out.println("targetClassName:" + targetClassName);
        map.put("targetClassName",targetClassName);
        //获取目标方法名
        Signature signature = jp.getSignature();
        String methodName = signature.getName();
        //System.out.println("methodName:" + methodName);
        map.put("methodName", methodName);
        //获取执行方法时你传入的参数
        Object[] args = jp.getArgs();
        String jsonArgs = new ObjectMapper().writeValueAsString(args);//把保存数据序列化为json数据
        //System.out.println(jsonArgs);
        map.put("jsonArgs",jsonArgs);

        long t1 = System.currentTimeMillis();
        System.out.println("start:" + t1);
        try {
            Object result = jp.proceed();
            long t2 = System.currentTimeMillis();
            long time = t2-t1;
            map.put("status", 1);
            map.put("usedTime", time);
            System.out.println("after:" + t2);
            return result;
        } catch (Throwable throwable) {
            long t3 = System.currentTimeMillis();
            long time = t3 -t1;
            map.put("status", 0);
            map.put("usedTime", time);
            map.put("error", throwable.getMessage());
            System.out.println("error:"+ t3);
            throw throwable;
        }finally {
            LOGGER.info("operation log {}", map.toString());
            //将map传递给用于进行日志记录或发送的服务
            remoteNoticeService.sendMsg(map);
        }
    }
    @Autowired
    private RemoteNoticeService remoteNoticeService;
}
