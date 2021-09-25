package com.erahub.jlja.authoritymanage.service;

import com.erahub.jlja.authoritymanage.dto.RoleDto;
import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erahub.jlja.common.lang.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lipeng
 * @since 2021-08-30
 */
public interface RoleService extends IService<Role> {

    /**
     * 获取角色列表
     * @param roleDto
     */
    Object getRoleList(RoleDto roleDto);

    /**
     * 新增角色信息
     * @param roleDto
     */
    Result saveRole(RoleDto roleDto);

    /**
     * 更新角色信息
     * @param roleDto
     */
    Result updateRole(RoleDto roleDto);

    /**
     * 删除角色信息
     * @param roleDtos
     */
    Result deleteRoles(List<RoleDto> roleDtos);

    /**
     * 赋予权限
     * @param roleDto
     */
    Result authorizePermission(RoleDto roleDto);
}
