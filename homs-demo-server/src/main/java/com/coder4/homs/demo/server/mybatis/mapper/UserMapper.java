package com.coder4.homs.demo.server.mybatis.mapper;

import com.coder4.homs.demo.server.mybatis.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2021-09-09
 */
@Repository
@Mapper
public interface UserMapper {

    long create(UserDO user);

    UserDO getUser(@Param("id") Long id);

    UserDO getUserByName(@Param("name") String name);

}
