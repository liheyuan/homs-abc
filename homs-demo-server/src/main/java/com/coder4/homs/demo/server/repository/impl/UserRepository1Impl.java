/**
 * @(#)UserRepository1Impl.java, 9月 09, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.repository.impl;

import com.coder4.homs.demo.server.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.coder4.homs.demo.server.repository.spi.UserRepository1;

import java.util.Optional;

/**
 * @author coder4
 */
@Repository
public class UserRepository1Impl extends BaseRepository implements UserRepository1 {

    private static RowMapper<User> ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

    @Override
    public Optional<Long> create(User user) {
        String sql = "INSERT INTO `users`(`name`) VALUES(:name)";
        SqlParameterSource param = new MapSqlParameterSource("name", user.getName());
        KeyHolder holder = new GeneratedKeyHolder();
        if (db.update(sql, param, holder) > 0) {
            return Optional.ofNullable(holder.getKey().longValue());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUser(long id) {
        String sql = "SELECT * FROM `user` WHERE `id` = :id";
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        try {
            return Optional.ofNullable(db.queryForObject(sql, param, ROW_MAPPER));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}