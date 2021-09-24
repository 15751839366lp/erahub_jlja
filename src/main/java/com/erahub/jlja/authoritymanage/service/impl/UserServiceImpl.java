package com.erahub.jlja.authoritymanage.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erahub.jlja.authoritymanage.dto.RoleDto;
import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.Permission;
import com.erahub.jlja.authoritymanage.entity.Role;
import com.erahub.jlja.authoritymanage.entity.User;
import com.erahub.jlja.authoritymanage.mapper.RoleMapper;
import com.erahub.jlja.authoritymanage.mapper.UserMapper;
import com.erahub.jlja.authoritymanage.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erahub.jlja.authoritymanage.utils.AuthorityUtils;
import com.erahub.jlja.authoritymanage.vo.UserVo;
import com.erahub.jlja.common.lang.Result;
import com.erahub.jlja.util.ListMapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 服务实现类
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
     * 获取当前登录用户数据
     *
     * @param user
     */
    @Override
    public UserVo getUserInfo(User user) {
        UserVo userVo = new UserVo();
        UserDto userDto = new UserDto();
        List<RoleDto> roleDtos = new ArrayList<>();
        Object menus = new Object();
        try {
            BeanUtils.copyProperties(user, userVo);
            userDto.setId(user.getId());
            //批量搜索
            List<Role> roles = userMapper.getUsersRoles(Arrays.asList(userDto));
            userVo.setRoles(roles);
            //复制role产生查询条件
            listMapUtils.copyList(roles, roleDtos, RoleDto.class);
            //批量搜索
            List<Permission> permissions = roleMapper.getUserPermissions(roleDtos);
            userVo.setPermissions(permissions);
            //按节点排序
            Collections.sort(permissions, new Comparator<Permission>() {
                public int compare(Permission arg0, Permission arg1) {
                    Long pid0 = arg0.getPid();
                    Long pid1 = arg1.getPid();
                    Long permissionId0 = arg0.getPermissionId();
                    Long permissionId1 = arg1.getPermissionId();
                    if (pid0 > pid1) {
                        return 1;
                    } else if (pid0 == pid1) {
                        if (permissionId0 > permissionId1) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else {
                        return -1;
                    }
                }
            });

            //生成菜单
            menus = listMapUtils.listToTree(permissions.stream().filter(permission -> permission.getIsMenu()).collect(Collectors.toList()),
                    "id", "pid", "subs");
            userVo.setMenus(menus);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userVo;
    }

    @Override
    public Object getUserList(UserDto userDto) {
        IPage<User> userPage = new Page<>(userDto.getPageIndex(), userDto.getPageSize());
        userPage = userMapper.selectPage(userPage, AuthorityUtils.getUserWrapper(userDto));
        return userPage;
    }

}
