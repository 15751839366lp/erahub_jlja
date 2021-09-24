package com.erahub.jlja.authoritymanage.service;

import com.erahub.jlja.authoritymanage.dto.UserDto;
import com.erahub.jlja.authoritymanage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erahub.jlja.authoritymanage.vo.UserVo;

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

}
