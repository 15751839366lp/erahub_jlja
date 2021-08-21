package com.erahub.jlja.shiro;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountProfile implements Serializable {

    /**
     * 唯一id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后登陆时间
     */
    private LocalDateTime lastLoginTime;
}
