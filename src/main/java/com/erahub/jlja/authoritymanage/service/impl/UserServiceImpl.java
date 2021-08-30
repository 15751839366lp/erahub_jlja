package com.erahub.jlja.authoritymanage.service.impl;

import com.erahub.jlja.authoritymanage.dto.RoleDto;
import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.Permission;
import com.erahub.jlja.authoritymanage.entity.Role;
import com.erahub.jlja.authoritymanage.entity.User;
import com.erahub.jlja.authoritymanage.mapper.RoleMapper;
import com.erahub.jlja.authoritymanage.mapper.UserMapper;
import com.erahub.jlja.authoritymanage.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erahub.jlja.authoritymanage.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lipeng
 * @since 2021-08-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    /**
     * 获取用户数据
     * @param user
     */
    @Override
    public UserVo getUserInfo(User user){
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        //TODO 修改为批量搜索
        List<Role> roles = userMapper.getUserRoles(userDto);
        userVo.setRoles(roles);
        List<Permission> permissions = new ArrayList<Permission>();
        //TODO 修改为批量搜索
        roles.forEach(role -> {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role,roleDto);
            List<Permission> temPermissions = roleMapper.getUserPermissions(roleDto);
            temPermissions.forEach(permission -> {
                if(!permissions.contains(permission)){
                    permissions.add(permission);
                }
            });
        });
        userVo.setPermissions(permissions);
        return userVo;
    }

}
