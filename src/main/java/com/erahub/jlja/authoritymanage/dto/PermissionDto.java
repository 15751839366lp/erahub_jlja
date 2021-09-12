package com.erahub.jlja.authoritymanage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PermissionDto implements Serializable {
    /**
     * 编号
     */
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
     * 权限路径
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
     * 创建时间（开始）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startCreateTime;

    /**
     * 创建时间（结束）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endCreateTime;

    /**
     * 更新时间（开始）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startUpdateTime;

    /**
     * 更新时间（结束）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endUpdateTime;

    /**
     * 删除时间（开始）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startDeleteTime;

    /**
     * 删除时间（结束）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
