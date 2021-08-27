package com.erahub.jlja.login.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erahub.jlja.common.dto.LoginDto;
import com.erahub.jlja.common.lang.Result;
import com.erahub.jlja.login.entity.User;
import com.erahub.jlja.login.service.UserService;
import com.erahub.jlja.util.JwtUtils;
import com.erahub.jlja.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    /**
     * 登录
     *
     */
    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
//        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
//            return Result.fail("密码错误！");
//        }
        if(!user.getPassword().equals(loginDto.getPassword())) {
            return Result.fail("密码错误");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return Result.succ(MapUtil.builder()
                .put("user",user)
                .map()
        );
    }

    /**
     * 登录
     *
     */
    @CrossOrigin
    @PostMapping("/getIndexData")
    public Result getIndexData() {
        User user = ShiroUtils.getUserFromSubject();
        return Result.succ(MapUtil.builder()
                .put("user",user)
                .map()
        );
    }


    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ("注销成功");
    }

}
