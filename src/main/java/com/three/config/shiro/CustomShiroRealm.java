package com.three.config.shiro;

import com.three.modules.sys.domain.SysUser;
import com.three.modules.sys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义域
 * Created by three on 2017/9/4.
 */
public class CustomShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        SysUser user = null;
        try{
            user = userService.findByUsername(username);
        }catch (Exception e){
            e.printStackTrace();
        }

        String salt=user.getSalt();
        if(user!=null){
            return new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(salt), getName());
        }else{
            return null;
        }
    }
}
