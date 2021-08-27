package com.erahub.jlja.util;

import com.erahub.jlja.login.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ShiroUtils {

    public static User getUserFromSubject(){
        Object principal = SecurityUtils.getSubject().getPrincipal();
        User user = new User();
        BeanUtils.copyProperties(principal,user);
        return user;
    }
}
