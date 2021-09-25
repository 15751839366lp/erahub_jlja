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
import com.erahub.jlja.shiro.AccountRealm;
import com.erahub.jlja.util.ListMapUtils;
import com.erahub.jlja.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
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

    @Override
    public Result saveUser(UserDto userDto) {
        if(userMapper.selectCount(new QueryWrapper<User>().eq("username",userDto.getUsername())) > 0){
            return Result.fail("用户名已存在");
        }

        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        userMapper.insert(user);
        return Result.succ("添加成功",null);
    }

    @Override
    public Result updateUser(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        int updateNum = userMapper.update(user, new QueryWrapper<User>().eq("id", userDto.getId()));
        if(updateNum > 0){
            return Result.succ("更新成功",null);
        }else {
            return Result.fail("更新失败");
        }
    }

    @Override
    public Result deleteUsers(List<UserDto> userDtos) {
        List<Long> ids = userDtos.stream().map(UserDto::getId).collect(Collectors.toList());
        Integer deleteAuthorityNum = userMapper.deleteAuthorityRole(ids);
        Integer deleteNum = userMapper.deleteBatchIds(ids);

        if(deleteNum > 0){
            return Result.succ("删除成功",null);
        }else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public Result authorizeRole(UserDto userDto){
        if(userDto.getRoleDtos() == null || userDto.getRoleDtos().size() <= 0){
            return Result.fail("授权为空");
        }
        Integer deleteAuthorityNum = userMapper.deleteAuthorityRole(Arrays.asList(userDto.getId()));
        Integer insertNum = userMapper.authorizeRole(userDto);
        if(insertNum > 0){
            RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
            AccountRealm realm = (AccountRealm)rsm.getRealms().iterator().next();
            realm.clearCachedAuthorization();
            return Result.succ("授权成功",null);
        }else {
            return Result.fail("授权失败");
        }
    }
}
