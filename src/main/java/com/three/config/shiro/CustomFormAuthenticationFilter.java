package com.three.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by three on 2017/9/4.
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(CustomFormAuthenticationFilter.class);

    /**
     * 所有请求请求回调
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }

    /**
     * 验证成功后回调
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("{result:true,message:''}");
        return false;
    }

    /**
     * 验证失败后回调
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
                out.println("{result:false,message:'密码错误'}");
            } else if ("UnknownAccountException".equals(message)) {
                out.println("{result:false,message:'账号不存在'}");
            } else if ("LockedAccountException".equals(message)) {
                out.println("{result:false,message:'账号被锁定'}");
            } else {
                out.println("{result:false,message:'未知错误'}");
            }
            out.flush();
//            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
            logger.error("CustomFormAuthenticationFilter error");
        }
        return false;
    }
}


