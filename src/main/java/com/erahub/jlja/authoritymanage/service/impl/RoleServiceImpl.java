package com.erahub.jlja.authoritymanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erahub.jlja.authoritymanage.dto.RoleDto;
import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.Role;
import com.erahub.jlja.authoritymanage.entity.User;
import com.erahub.jlja.authoritymanage.mapper.RoleMapper;
import com.erahub.jlja.authoritymanage.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erahub.jlja.authoritymanage.utils.AuthorityUtils;
import com.erahub.jlja.common.lang.Result;
import com.erahub.jlja.shiro.AccountRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lipeng
 * @since 2021-08-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Object getRoleList(RoleDto roleDto) {
        IPage<Role> rolePage = new Page<>(roleDto.getPageIndex(), roleDto.getPageSize());
        rolePage = roleMapper.selectPage(rolePage, AuthorityUtils.getRoleWrapper(roleDto));
        return rolePage;
    }

    @Override
    public Result saveRole(RoleDto roleDto) {
        if(roleMapper.selectCount(new QueryWrapper<Role>().eq("role",roleDto.getRole())) > 0){
            return Result.fail("角色名已存在");
        }

        Role role = new Role();
        BeanUtils.copyProperties(roleDto,role);
        roleMapper.insert(role);
        return Result.succ("添加成功",null);
    }

    @Override
    public Result updateRole(RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto,role);
        int updateNum = roleMapper.update(role, new QueryWrapper<Role>().eq("id", roleDto.getId()));
        if(updateNum > 0){
            return Result.succ("更新成功",null);
        }else {
            return Result.fail("更新失败");
        }
    }

    @Override
    public Result deleteRoles(List<RoleDto> roleDtos) {
        List<Long> ids = roleDtos.stream().map(RoleDto::getId).collect(Collectors.toList());
        Integer deleteAuthorityNum = roleMapper.deleteAuthorityPermission(ids);
        Integer deleteNum = roleMapper.deleteBatchIds(ids);

        if(deleteNum > 0){
            return Result.succ("删除成功",null);
        }else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public Result authorizePermission(RoleDto roleDto) {
        if(roleDto.getPermissionDtos() == null || roleDto.getPermissionDtos().size() <= 0){
            return Result.fail("授权为空");
        }
        Integer deleteAuthorityNum = roleMapper.deleteAuthorityPermission(Arrays.asList(roleDto.getId()));
        Integer insertNum = roleMapper.authorizePermission(roleDto);
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
