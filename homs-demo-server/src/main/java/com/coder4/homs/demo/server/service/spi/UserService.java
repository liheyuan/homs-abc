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

/**
 * @author coder4
 */
public interface UserService {

    Optional<Long> create(User toUser);

    Optional<User> getUserById(long id);

    Optional<Long> createV2(User toUser);
}