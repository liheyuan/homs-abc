/**
 * @(#)UserLogic.java, 9月 09, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.web.logic.spi;

import com.coder4.homs.demo.server.web.vo.UserVO;

/**
 * @author coder4
 */
public interface UserLogic {

    long createUser(UserVO user);

    UserVO getUserById(long id);

    UserVO getUserByName(String name);

    long createUserV2(UserVO user);

    UserVO getUserByIdV2(long id);

    UserVO getUserByNameV2(String name);

    long createUserV3(UserVO user);

    UserVO getUserByIdV3(long id);

    UserVO getUserByNameV3(String name);
}