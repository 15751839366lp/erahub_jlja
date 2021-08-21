package com.erahub.jlja.shiro;

import com.erahub.jlja.entity.User;
import com.erahub.jlja.service.UserService;
import com.erahub.jlja.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //登陆处理
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;

        //userId一般放在subject中
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if(user == null){
            throw new UnknownAccountException("账号不存在");
        }
        if(user.getStatus() == -1){
            throw new LockedAccountException("账号已被锁定");
        }

        //将用户数据复制当封装的AccountProfile类中
        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(user,profile);


        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}
