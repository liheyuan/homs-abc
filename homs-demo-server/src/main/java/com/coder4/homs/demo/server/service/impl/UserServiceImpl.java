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
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coder4.homs.demo.server.repository.spi.UserRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;

import static io.github.resilience4j.bulkhead.annotation.Bulkhead.Type.THREADPOOL;

/**
 * @author coder4
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    private Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private ExecutorService threadPool = Executors.newFixedThreadPool(5);

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
    @CircuitBreaker(name = "getUserById", fallbackMethod = "getUserByIdFallback")
    public Optional<User> getUserById(long id) {
        // Mock a failure
        if (ThreadLocalRandom.current().nextInt(100) < 90) {
            throw new RuntimeException("mock failure");
        }
        return userRepository.getUser(id);
    }

    public Optional<User> getUserByIdFallback(long id, Throwable e) {
        LOG.error("enter fallback for getUserById", e);
        return Optional.empty();
    }

    @Override
    @Bulkhead(name = "getUserByName", type = Type.THREADPOOL)
    @TimeLimiter(name = "getUserByName", fallbackMethod = "getUserByNameWithCompletableFutureFallback")
    public CompletableFuture<Optional<User>> getUserByNameWithCompletableFuture(String name) {
        // Mock timeout
        Try.run(() -> Thread.sleep(ThreadLocalRandom.current().nextInt(2000)));

        return CompletableFuture.completedFuture(userRepository.getUserByName(name));
    }

    public CompletableFuture<Optional<User>> getUserByNameWithCompletableFutureFallback(String name, Throwable e) {
        LOG.error("enter fallback for getUserByNameFallback", e);
        return CompletableFuture.completedFuture(Optional.empty());
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
    @RateLimiter(name = "getUserByIdV2", fallbackMethod = "getUserByIdV2Fallback")
    public Optional<User> getUserByIdV2(long id) {
        return Optional.ofNullable(userMapper.getUser(id)).map(UserDO::toUser);
    }

    public Optional<User> getUserByIdV2Fallback(long id, Throwable e) {
        LOG.error("getUserByIdV2 fallback exception", e);
        return Optional.empty();
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
        //return userJPARepository.findById(id).map(UserEntity::toUser);
        return userJPARepository.findByIdFast(id).map(UserEntity::toUser);
    }

    @Override
    public Optional<User> getUserByNameV3(String name) {
        return userJPARepository.findByName(name).stream().findFirst().map(UserEntity::toUser);
    }

}