package com.three.config.shiro;

import com.three.common.util.BaseUtil;
import com.three.common.util.Const;
import com.three.modules.sys.domain.SysUser;
import com.three.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * Created by three on 2017/9/4.
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(CustomFormAuthenticationFilter.class);

    @Autowired
    UserService userService;

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
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        SysUser user = userService.findByUsername(username);

        //验证成功后，在session中存储用户的基本信息
        subject.getSession().setAttribute(Const.CURRENT_USER,user);
        //更新上次ip和时间
        userService.editLoginInfo(new SysUser(user.getId(), BaseUtil.getIpAddress((HttpServletRequest)request),new Timestamp(System.currentTimeMillis())));
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
            } else if("AuthenticationException".equals(message)){
                out.println("{result:false,message:'账号或密码错误'}");
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


