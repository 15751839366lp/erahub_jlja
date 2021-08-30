package com.erahub.jlja.authoritymanage.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erahub.jlja.authoritymanage.dto.LoginDto;
import com.erahub.jlja.authoritymanage.vo.UserVo;
import com.erahub.jlja.common.lang.Result;
import com.erahub.jlja.authoritymanage.entity.User;
import com.erahub.jlja.authoritymanage.service.UserService;
import com.erahub.jlja.util.JwtUtils;
import com.erahub.jlja.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("username", loginDto.getUsername())
                .eq("password", loginDto.getPassword())
                .eq("deleted",false));
        Assert.notNull(user, "用户名或密码错误");
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
    @PostMapping("/getUserInfo")
    public Result getUserInfo() {
        User user = ShiroUtils.getUserFromSubject();
        UserVo userVo = userService.getUserInfo(user);
        return Result.succ(MapUtil.builder()
                .put("user",userVo)
                .map()
        );
    }


    // 退出
    //    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ("注销成功");
    }

}
