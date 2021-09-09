/**
 * @(#)UserServiceImpl.java, 9月 09, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.service.impl;

import com.coder4.homs.demo.server.model.User;
import com.coder4.homs.demo.server.mybatis.dataobject.UserDO;
import com.coder4.homs.demo.server.mybatis.mapper.UserMapper;
import com.coder4.homs.demo.server.service.spi.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coder4.homs.demo.server.repository.spi.UserRepository1;

import java.util.Optional;

/**
 * @author coder4
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserRepository1 userRepository1;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<Long> create(User user) {
        return userRepository1.create(user);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository1.getUser(id);
    }

    @Override
    public Optional<Long> createV2(User toUser) {
        UserDO userDO = toUser.toUserDO();
        if (userMapper.insert(userDO) > 0) {
            return Optional.ofNullable(userDO.getId());
        }
        return Optional.empty();
    }

}