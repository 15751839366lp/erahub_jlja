package com.erahub.jlja.authoritymanage.controller;

import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.User;
import com.erahub.jlja.common.lang.Result;
import com.erahub.jlja.authoritymanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 更新用户信息
     * @param userDto
     * @return
     */
    @CrossOrigin
    @PostMapping("/updateUserData")
    public Result updateUserData(@RequestBody UserDto userDto){
        return Result.succ(null);
    }

    /**
     * 删除用户信息
     * @param userDtos
     * @return
     */
    @CrossOrigin
    @PostMapping("/deleteUserData")
    public Result deleteUserData(@RequestBody List<UserDto> userDtos){
        return Result.succ(null);
    }
}
