package com.three.common.aspect;

import com.three.common.annotation.LogAction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by three on 2017/9/2.
 */
@Component
@Aspect
public class LogAspect {

    @Pointcut("@annotation(com.three.common.annotation.LogAction)")
    public void annotationPointCut(){};


    //通过上面的切点进行拦截
    @After("annotationPointCut()")
    public void after(JoinPoint joinpoint){
        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        Method method = signature.getMethod();
        LogAction annotation = method.getAnnotation(LogAction.class);
        System.out.println(annotation.name());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String cs= (String) request.getAttribute("cs");
        System.out.println(cs);
    }

    //通过参数规则进行拦截
    @Before("execution(* com.three.controller.LogTestController.*(..))")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println(method.getName());
    }
}
