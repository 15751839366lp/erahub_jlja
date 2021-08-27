package com.erahub.jlja.login.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author lipeng
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 创建时间
     */
    @JsonSerialize
    @JsonDeserialize
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonSerialize
    @JsonDeserialize
    private LocalDateTime updateTime;

    /**
     * 最后登录时间
     */
    @JsonSerialize
    @JsonDeserialize
    private LocalDateTime lastLogin;

    /**
     * 删除时间
     */
    @JsonSerialize
    @JsonDeserialize
    private LocalDateTime deleteTime;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 是否锁定
     */
    private Boolean locked;


}
