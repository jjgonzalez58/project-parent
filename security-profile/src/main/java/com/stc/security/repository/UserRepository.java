package com.stc.security.repository;

import com.stc.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * User: job
 * Date: 16/04/22
 * Time: 15:27
 *
 * @author job
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.alias = ?1 and u.password = ?2")
    User findUserByAliasAndPass(String alias, String password);
}
