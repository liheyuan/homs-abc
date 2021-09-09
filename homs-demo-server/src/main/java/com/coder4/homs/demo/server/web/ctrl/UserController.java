/**
 * @(#)HelloController.java, 8月 13, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.web.ctrl;

import com.coder4.homs.demo.server.web.logic.spi.UserLogic;
import com.coder4.homs.demo.server.web.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coder4
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserLogic userLogic;

    @PostMapping
    public long createUser(@RequestBody UserVO user) {
        return userLogic.createUser(user);
    }

    @GetMapping(value = "/{id}")
    public UserVO getById(@PathVariable long id) {
        return userLogic.getUserById(id);
    }

    @PostMapping(value = "/v2")
    public long createUserV2(@RequestBody UserVO user) {
        return userLogic.createUserV2(user);
    }

}