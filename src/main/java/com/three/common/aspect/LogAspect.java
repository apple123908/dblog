package com.three.common.aspect;

import com.three.common.annotation.LogAction;
import com.three.common.domain.SysLog;
import com.three.common.service.LogService;
import com.three.common.util.BaseUtil;
import com.three.common.util.Const;
import com.three.modules.sys.domain.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.sql.Timestamp;

/**
 * Created by three on 2017/9/2.
 */
@Component
@Aspect
public class LogAspect {


    @Autowired
    LogService logService;

    @Pointcut("@annotation(com.three.common.annotation.LogAction)")
    public void annotationPointCut(){};


    //通过上面的切点进行拦截
    @After("annotationPointCut()")
    public void after(JoinPoint joinpoint){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute(Const.CURRENT_USER);
        String ip = BaseUtil.getIpAddress(request);//ip
        String username = user.getUsername();//用户名


        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        Method method = signature.getMethod();
        LogAction annotation = method.getAnnotation(LogAction.class);
        String action = annotation.name();//动作

        SysLog log = new SysLog(username,action,new Timestamp(System.currentTimeMillis()),ip);
        logService.addLog(log);
    }

    //通过参数规则进行拦截
    /*@Before("execution(* com.three.controller.LogTestController.*(..))")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println(method.getName());
    }*/
}
