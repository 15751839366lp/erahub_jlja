package com.erahub.jlja.login.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erahub.jlja.common.dto.LoginDto;
import com.erahub.jlja.common.lang.Result;
import com.erahub.jlja.login.entity.User;
import com.erahub.jlja.login.service.UserService;
import com.erahub.jlja.util.JwtUtils;
import com.google.common.collect.ImmutableMap;
import net.sf.saxon.expr.Component;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lipeng
 * @since 2021-08-23
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    // 退出
    @GetMapping("/toLogin")
    public String toLogin() {
        return "/login/login";
    }

    /**
     * 登录
     *
     */
    @ResponseBody
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
        // 用户可以另一个接口
//        model.addAllAttributes();
        return Result.succ(MapUtil.builder()
                .put("user",user)
                .put("token",jwt)
                .map()
        );
    }

//    @ResponseBody
    @GetMapping("/jump/toIndex")
    @RequiresAuthentication
    public String toIndex() {
        return "index";
    }

    /**
     * 登录
     *
     */
    @ResponseBody
    @CrossOrigin
    @PostMapping("/getIndexData")
    public Result login() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return Result.succ(MapUtil.builder()
                .put("user",user)
                .map()
        );
    }


    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "/login/login";
    }

}
