/**
 * @(#)UserLogicImpl.java, 9月 09, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.web.logic.impl;

import com.coder4.homs.demo.server.model.User;
import com.coder4.homs.demo.server.service.spi.UserService;
import com.coder4.homs.demo.server.web.logic.spi.UserLogic;
import com.coder4.homs.demo.server.web.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author coder4
 */
@Service
public class UserLogicImpl implements UserLogic {

    @Autowired
    private UserService userService;

    @Override
    public long createUser(UserVO user) {
        return userService.create(user.toUser()).orElse(-1L);
    }

    @Override
    public UserVO getUserById(long id) {
        return userService.getUserById(id).map(User::toUserVO)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @Override
    public long createUserV2(UserVO user) {
        return userService.createV2(user.toUser()).orElse(-1L);
    }

}