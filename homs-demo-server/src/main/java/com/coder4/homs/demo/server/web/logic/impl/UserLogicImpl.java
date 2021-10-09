/**
 * @(#)UserLogicImpl.java, 9月 09, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.web.logic.impl;

import com.coder4.homs.demo.server.model.User;
import com.coder4.homs.demo.server.service.impl.UserServiceImpl;
import com.coder4.homs.demo.server.service.spi.UserService;
import com.coder4.homs.demo.server.web.logic.spi.UserLogic;
import com.coder4.homs.demo.server.web.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author coder4
 */
@Service
public class UserLogicImpl implements UserLogic {

    private Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LettuceConnectionFactory leconnFactory;

    @Override
    public long createUser(UserVO user) {
        return userService.create(user.toUser()).orElse(-1L);
    }

    @Override
    public UserVO getUserById(long id) {
        // redisTemplate.boundValueOps("key").set("value");

//        try (RedisConnection conn = leconnFactory.getConnection()) {
//            conn.set("hehe".getBytes(), "haha".getBytes());
//        }
        return userService.getUserById(id).map(User::toUserVO)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @Override
    public UserVO getUserByName(String name) {
        try {
            return userService.getUserByNameWithCompletableFuture(name).get().map(User::toUserVO)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        } catch (Exception e) {
            LOG.error("getUserByNameWithCompletableFuture exception", e);
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @Override
    public long createUserV2(UserVO user) {
        return userService.createV2(user.toUser()).orElse(-1L);
    }

    @Override
    public UserVO getUserByIdV2(long id) {
        return userService.getUserByIdV2(id).map(User::toUserVO)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @Override
    public UserVO getUserByNameV2(String name) {
        return userService.getUserByNameV2(name).map(User::toUserVO)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @Override
    public long createUserV3(UserVO user) {
        return userService.createV3(user.toUser()).orElse(-1L);
    }

    @Override
    public UserVO getUserByIdV3(long id) {
        return userService.getUserByIdV3(id).map(User::toUserVO)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @Override
    public UserVO getUserByNameV3(String name) {
        return userService.getUserByNameV3(name).map(User::toUserVO)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

}