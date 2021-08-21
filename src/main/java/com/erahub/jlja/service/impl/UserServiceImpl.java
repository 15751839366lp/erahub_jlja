package com.erahub.jlja.service.impl;

import com.erahub.jlja.entity.User;
import com.erahub.jlja.mapper.UserMapper;
import com.erahub.jlja.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lipeng
 * @since 2021-08-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
