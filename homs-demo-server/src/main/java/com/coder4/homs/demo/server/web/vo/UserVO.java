/**
 * @(#)UserVO.java, 9月 09, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.web.vo;

import com.coder4.homs.demo.server.model.User;
import lombok.Data;

/**
 * @author coder4
 */
@Data
public class UserVO {

    private long id;

    private String name;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }
}