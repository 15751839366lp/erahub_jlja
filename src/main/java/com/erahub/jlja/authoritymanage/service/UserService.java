package com.erahub.jlja.authoritymanage.service;

import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erahub.jlja.authoritymanage.vo.UserVo;
import com.erahub.jlja.common.lang.Result;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lipeng
 * @since 2021-08-23
 */
public interface UserService extends IService<User> {

    /**
     * 获取用户数据
     * @param user
     */
    UserVo getUserInfo(User user);

    /**
     * 获取用户列表
     * @param userDto
     */
    Object getUserList(UserDto userDto);

    /**
     * 添加用户数据
     * @param userDto
     */
    Result saveUser(UserDto userDto);

    /**
     * 更新用户数据
     * @param userDto
     */
    Result updateUser(UserDto userDto);

    /**
     * 删除用户数据
     * @param userDtos
     */
    Result deleteUsers(List<UserDto> userDtos);

    /**
     * 赋予角色
     * @param userDto
     */
    Result authorizeRole(UserDto userDto);
}
