package com.erahub.jlja;


import com.erahub.jlja.authoritymanage.dto.RoleDto;
import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.Permission;
import com.erahub.jlja.authoritymanage.entity.Role;
import com.erahub.jlja.authoritymanage.mapper.UserMapper;
import com.erahub.jlja.authoritymanage.service.PermissionService;
import com.erahub.jlja.authoritymanage.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class JljaApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PermissionService permissionService;

    @Autowired
    RoleService roleService;

    @Test
    void contextLoads() {
    }

    @Test
    void queryData(){
//        Permission permission = new Permission();
//        permission.setPermission("权限管理");
//        permission.setIsMenu(true);
//        permission.setPid(1L);
//        permissionService.save(permission);

//        Role role = new Role();
//        role.setRole("superadmin");
//        roleService.save(role);s

//        UserDto userDto = new UserDto();
//        userDto.setId(3L);
//        List<Role> roles = userMapper.getUserRoles(userDto);
//        roles.forEach(role -> {
//            RoleDto roleDto = new RoleDto();
//            BeanUtils.copyProperties(role,roleDto);
//            System.out.println(roleDto.toString());
//        });
    }
}
