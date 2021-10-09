/**
 * @(#)UserService.java, 9月 09, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.service.spi;

import com.coder4.homs.demo.server.model.User;
import com.coder4.homs.demo.server.web.vo.UserVO;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author coder4
 */
public interface UserService {

    Optional<Long> create(User user);

    Optional<User> getUserById(long id);

    CompletableFuture<Optional<User>> getUserByNameWithCompletableFuture(String name);

    Optional<Long> createV2(User user);

    Optional<User> getUserByIdV2(long id);

    Optional<User> getUserByNameV2(String name);

    Optional<Long> createV3(User user);

    Optional<User> getUserByIdV3(long id);

    Optional<User> getUserByNameV3(String name);
}