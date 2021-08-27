package com.erahub.jlja.login.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erahub.jlja.common.dto.LoginDto;
import com.erahub.jlja.common.lang.Result;
import com.erahub.jlja.login.entity.User;
import com.erahub.jlja.login.service.UserService;
import com.erahub.jlja.util.JwtUtils;
import com.erahub.jlja.util.ShiroUtils;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.springframework.beans.BeanUtils;
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
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/getUserDataList")
    public Result getUserDataList(){

        return Result.succ(null);
    }
}
