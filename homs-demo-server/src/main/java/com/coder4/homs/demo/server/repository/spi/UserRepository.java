/**
 * @(#)UserRepository.java, 9月 09, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.repository.spi;

import com.coder4.homs.demo.server.model.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

/**
 * @author coder4
 */
public interface UserRepository {

    Optional<Long> create(User user);

    @Cacheable(value = "cache")
    Optional<User> getUser(long id);

    Optional<User> getUserByName(String name);
}