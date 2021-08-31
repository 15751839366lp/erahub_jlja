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
import com.erahub.jlja.util.ListMapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    ListMapUtils listMapUtils;

    /**
     * 获取用户数据
     * @param user
     */
    @Override
    public UserVo getUserInfo(User user){
        UserVo userVo = new UserVo();
        UserDto userDto = new UserDto();
        List<RoleDto> roleDtos = new ArrayList<>();
        try{
            BeanUtils.copyProperties(user,userVo);
            userDto.setId(user.getId());
            //批量搜索
            List<Role> roles = userMapper.getUsersRoles(Arrays.asList(userDto));
            userVo.setRoles(roles);
            //复制role产生查询条件
            listMapUtils.copyList(roles,roleDtos,RoleDto.class);
            //批量搜索
            List<Permission> permissions = roleMapper.getUserPermissions(roleDtos);
            userVo.setPermissions(permissions);
        }catch (Exception e){
            e.printStackTrace();
        }

        return userVo;
    }

}
