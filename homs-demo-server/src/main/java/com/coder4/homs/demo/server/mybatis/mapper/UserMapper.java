package com.coder4.homs.demo.server.mybatis.mapper;

import com.coder4.homs.demo.server.mybatis.dataobject.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Insert("INSERT INTO users(name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    long create(UserDO user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    UserDO getUser(@Param("id") Long id);

    @Select("SELECT * FROM users WHERE name = #{name}")
    UserDO getUserByName(@Param("name") String name);

}
