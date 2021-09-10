/**
 * @(#)UserPO.java, 9月 09, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.model;

import com.coder4.homs.demo.server.jpa.entity.UserEntity;
import com.coder4.homs.demo.server.mybatis.dataobject.UserDO;
import com.coder4.homs.demo.server.web.vo.UserVO;
import lombok.Data;

/**
 * @author coder4
 */
@Data
public class User {

    private long id;

    private String name;

    public UserVO toUserVO() {
        UserVO user = new UserVO();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public UserDO toUserDO() {
        UserDO user = new UserDO();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public UserEntity toUserEntity() {
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setName(name);
        return user;
    }
}