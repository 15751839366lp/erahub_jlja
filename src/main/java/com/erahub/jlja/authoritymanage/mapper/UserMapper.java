package com.erahub.jlja.authoritymanage.mapper;

import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.Role;
import com.erahub.jlja.authoritymanage.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lipeng
 * @since 2021-08-23
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户获取角色信息
     * @param userDtos
     * @return
     */
    List<Role> getUsersRoles(@Param("userDtos") List<UserDto> userDtos);

}
