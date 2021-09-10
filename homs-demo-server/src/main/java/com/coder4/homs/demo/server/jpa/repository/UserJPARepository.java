/**
 * @(#)UserRepository.java, 9月 10, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.jpa.repository;

import com.coder4.homs.demo.server.jpa.entity.UserEntity;
import com.coder4.homs.demo.server.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author coder4
 */
@Repository
public interface UserJPARepository extends CrudRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    Optional<UserEntity> findByIdFast(@Param("id") long id);

    Collection<UserEntity> findByName(String name);

}