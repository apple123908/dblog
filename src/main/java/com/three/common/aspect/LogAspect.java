package com.three.common.aspect;

import com.three.common.annotation.LogAction;
import com.three.common.domain.SysLog;
import com.three.common.service.LogService;
import com.three.common.util.BaseUtil;
import com.three.common.util.Const;
import com.three.common.util.JsonUtil;
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
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

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
    @Before("annotationPointCut()")
    public void before(JoinPoint joinpoint){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute(Const.CURRENT_USER);
        String ip = BaseUtil.getIpAddress(request);//ip
        String username = user.getUsername();//用户名


        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        Method method = signature.getMethod();
        LogAction annotation = method.getAnnotation(LogAction.class);
        String action = annotation.name();//动作

        //TODO 参数
        Object[] args = joinpoint.getArgs();
        int i=1;
        Map<String,Object> map=new HashMap<String,Object>();
        String parameterBefore=null;
        for(Object arg : args){
            map.put("第"+i+"个参数",JsonUtil.object2String(arg));
        }
        if(map.size()!=0){
            parameterBefore=JsonUtil.object2String(map);
        }

        SysLog log = new SysLog(username,action,new Timestamp(System.currentTimeMillis()),ip,parameterBefore);
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
