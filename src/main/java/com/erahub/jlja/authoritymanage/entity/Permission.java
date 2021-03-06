package com.erahub.jlja.authoritymanage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author lipeng
 * @since 2021-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限编号
     */
    private Long permissionId;

    /**
     * 权限名称
     */
    private String permission;

    /**
     * 权限访问路径
     */
    private String url;

    /**
     * 是否为菜单
     */
    private Boolean isMenu;

    /**
     * 是否有子节点
     */
    private Boolean hasChildren;

    /**
     * 权限标识
     */
    private String perm;

    /**
     * 图标
     */
    private String icon;

    /**
     * 父节点（根节点为0）
     */
    private Long pid;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除时间
     */
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
