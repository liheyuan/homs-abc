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

    long createUserV2(UserVO user);
}