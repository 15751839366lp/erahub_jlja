package com.erahub.jlja.authoritymanage.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDto implements Serializable {

    /**
     * 编号
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 创建时间（开始）
     */
    private LocalDateTime startCreateTime;

    /**
     * 创建时间（结束）
     */
    private LocalDateTime endCreateTime;

    /**
     * 更新时间（开始）
     */
    private LocalDateTime startUpdateTime;

    /**
     * 更新时间（结束）
     */
    private LocalDateTime endUpdateTime;

    /**
     * 最后登录时间（开始）
     */
    private LocalDateTime startLastLogin;

    /**
     * 最后登录时间（结束）
     */
    private LocalDateTime endLastLogin;

    /**
     * 删除时间（开始）
     */
    private LocalDateTime startDeleteTime;

    /**
     * 删除时间（结束）
     */
    private LocalDateTime endDeleteTime;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 是否锁定
     */
    private Boolean locked;

}
