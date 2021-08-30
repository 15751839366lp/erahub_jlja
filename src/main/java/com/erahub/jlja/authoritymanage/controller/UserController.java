package com.erahub.jlja.authoritymanage.controller;

import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.common.lang.Result;
import com.erahub.jlja.authoritymanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @PostMapping("/getUserList")
    public Result getUserList(@RequestBody UserDto userDto){
        return Result.succ(null);
    }
}
