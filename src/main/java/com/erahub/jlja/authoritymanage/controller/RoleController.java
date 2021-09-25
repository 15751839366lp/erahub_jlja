package com.erahub.jlja.authoritymanage.controller;


import com.erahub.jlja.authoritymanage.dto.RoleDto;
import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.service.RoleService;
import com.erahub.jlja.common.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lipeng
 * @since 2021-08-30
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * 获取角色列表（可条件查询）
     * @param roleDto
     * @return
     */
    @CrossOrigin
    @PostMapping("/getRoleList")
    public Result getRoleList(@RequestBody RoleDto roleDto){
        return Result.succ(roleService.getRoleList(roleDto));
    }

    /**
     * 新增角色信息
     * @param roleDto
     * @return
     */
    @CrossOrigin
    @PostMapping("/addRoleData")
    public Result addRoleData(@RequestBody RoleDto roleDto){
        return roleService.saveRole(roleDto);
    }

    /**
     * 更新角色信息
     * @param roleDto
     * @return
     */
    @CrossOrigin
    @PostMapping("/updateRoleData")
    public Result updateRoleData(@RequestBody RoleDto roleDto){
        return roleService.updateRole(roleDto);
    }

    /**
     * 删除角色信息
     * @param roleDtos
     * @return
     */
    @CrossOrigin
    @PostMapping("/deleteRoleData")
    public Result deleteRoleData(@RequestBody List<RoleDto> roleDtos){
        return roleService.deleteRoles(roleDtos);
    }

    /**
     * 赋予权限
     * @param roleDto
     * @return
     */
    @CrossOrigin
    @PostMapping("/authorizePermission")
    public Result authorizePermission(@RequestBody RoleDto roleDto){
        return roleService.authorizePermission(roleDto);
    }
}
