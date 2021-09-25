package com.erahub.jlja.authoritymanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erahub.jlja.authoritymanage.dto.RoleDto;
import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.User;
import com.erahub.jlja.common.lang.Result;
import com.erahub.jlja.authoritymanage.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lipeng
 * @since 2021-08-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取用户列表（可条件查询）
     * @param userDto
     * @return
     */
    @CrossOrigin
    @PostMapping("/getUserList")
    public Result getUserList(@RequestBody UserDto userDto){
        return Result.succ(userService.getUserList(userDto));
    }

    /**
     * 新增用户信息
     * @param userDto
     * @return
     */
    @CrossOrigin
    @PostMapping("/addUserData")
    public Result addUserData(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    /**
     * 更新用户信息
     * @param userDto
     * @return
     */
    @CrossOrigin
    @PostMapping("/updateUserData")
    public Result updateUserData(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }

    /**
     * 删除用户信息
     * @param userDtos
     * @return
     */
    @CrossOrigin
    @PostMapping("/deleteUserData")
    public Result deleteUserData(@RequestBody List<UserDto> userDtos){
        return userService.deleteUsers(userDtos);
    }

    /**
     * 赋予角色
     * @param userDto
     * @return
     */
    @CrossOrigin
    @PostMapping("/authorizeRole")
    public Result authorizeRole(@RequestBody UserDto userDto){
        return userService.authorizeRole(userDto);
    }
}
