package com.erahub.jlja.authoritymanage.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erahub.jlja.authoritymanage.dto.RoleDto;
import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.Role;
import com.erahub.jlja.authoritymanage.entity.User;
import org.springframework.util.StringUtils;

public class AuthorityUtils {

    public static QueryWrapper<User> getUserWrapper(UserDto userDto){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(userDto.getId())){
            wrapper.eq("id", userDto.getId());
        }
        if(!StringUtils.isEmpty(userDto.getUsername())){
            wrapper.eq("username", userDto.getUsername());
        }
        if(!StringUtils.isEmpty(userDto.getTrueName())){
            wrapper.eq("true_name", userDto.getTrueName());
        }
        if(!StringUtils.isEmpty(userDto.getDeleted())){
            wrapper.eq("deleted", userDto.getDeleted());
        }else{
            wrapper.eq("deleted", false);
        }
        if(!StringUtils.isEmpty(userDto.getLocked())){
            wrapper.eq("locked", userDto.getLocked());
        }
        if(!StringUtils.isEmpty(userDto.getStartCreateTime())){
            wrapper.apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') >= date_format('" + userDto.getStartCreateTime() + "','%Y-%m-%d %H:%i:%s')");
        }
        if(!StringUtils.isEmpty(userDto.getEndCreateTime())){
            wrapper.apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') <= date_format('" + userDto.getEndCreateTime() + "','%Y-%m-%d %H:%i:%s')");
        }
        return wrapper;
    }

    public static QueryWrapper<Role> getRoleWrapper(RoleDto roleDto){
        QueryWrapper<Role> wrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(roleDto.getId())){
            wrapper.eq("id", roleDto.getId());
        }
        if(!StringUtils.isEmpty(roleDto.getRole())){
            wrapper.eq("role", roleDto.getRole());
        }
        if(!StringUtils.isEmpty(roleDto.getDeleted())){
            wrapper.eq("deleted", roleDto.getDeleted());
        }else{
            wrapper.eq("deleted", false);
        }
        if(!StringUtils.isEmpty(roleDto.getLocked())){
            wrapper.eq("locked", roleDto.getLocked());
        }
        if(!StringUtils.isEmpty(roleDto.getStartCreateTime())){
            wrapper.apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') >= date_format('" + roleDto.getStartCreateTime() + "','%Y-%m-%d %H:%i:%s')");
        }
        if(!StringUtils.isEmpty(roleDto.getEndCreateTime())){
            wrapper.apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') <= date_format('" + roleDto.getEndCreateTime() + "','%Y-%m-%d %H:%i:%s')");
        }
        return wrapper;
    }
}
