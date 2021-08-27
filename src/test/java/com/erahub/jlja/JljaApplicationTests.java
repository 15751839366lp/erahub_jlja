package com.erahub.jlja;

import cn.hutool.core.map.MapUtil;
import com.erahub.jlja.login.entity.User;
import com.erahub.jlja.login.service.UserService;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
class JljaApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void queryData(){

    }
}
