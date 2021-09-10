/**
 * @(#)UserServiceImpl.java, 9月 09, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.service.impl;

import com.coder4.homs.demo.server.jpa.entity.UserEntity;
import com.coder4.homs.demo.server.jpa.repository.UserJPARepository;
import com.coder4.homs.demo.server.model.User;
import com.coder4.homs.demo.server.mybatis.dataobject.UserDO;
import com.coder4.homs.demo.server.mybatis.mapper.UserMapper;
import com.coder4.homs.demo.server.service.spi.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coder4.homs.demo.server.repository.spi.UserRepository;

import java.util.Optional;

/**
 * @author coder4
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserJPARepository userJPARepository;

    @Override
    public Optional<Long> create(User user) {
        return userRepository.create(user);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.getUser(id);
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return userRepository.getUserByName(name);
    }

    @Override
    public Optional<Long> createV2(User toUser) {
        UserDO userDO = toUser.toUserDO();
        if (userMapper.create(userDO) > 0) {
            return Optional.ofNullable(userDO.getId());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByIdV2(long id) {
        return Optional.ofNullable(userMapper.getUser(id)).map(UserDO::toUser);
    }

    @Override
    public Optional<User> getUserByNameV2(String name) {
        return Optional.ofNullable(userMapper.getUserByName(name))
                .map(UserDO::toUser);
    }

    @Override
    public Optional<Long> createV3(User user) {
        UserEntity userEntity = userJPARepository.save(user.toUserEntity());
        return Optional.of(userEntity.getId());
    }

    @Override
    public Optional<User> getUserByIdV3(long id) {
        // return userJPARepository.findById(id).map(UserEntity::toUser);
        return userJPARepository.findByIdFast(id).map(UserEntity::toUser);
    }

    @Override
    public Optional<User> getUserByNameV3(String name) {
        return userJPARepository.findByName(name).stream().findFirst().map(UserEntity::toUser);
    }

}