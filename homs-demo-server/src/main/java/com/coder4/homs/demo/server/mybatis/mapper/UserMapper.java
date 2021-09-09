package com.coder4.homs.demo.server.mybatis.mapper;

import com.coder4.homs.demo.server.mybatis.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
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

    long insert(UserDO user);

}
