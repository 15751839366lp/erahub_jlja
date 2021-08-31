package com.erahub.jlja.authoritymanage.mapper;

import com.erahub.jlja.authoritymanage.dto.RoleDto;
import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.Permission;
import com.erahub.jlja.authoritymanage.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lipeng
 * @since 2021-08-30
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据角色获取权限信息
     * @param roleDtos
     * @return
     */
    List<Permission> getUserPermissions(@Param("roleDtos")List<RoleDto> roleDtos);
}
