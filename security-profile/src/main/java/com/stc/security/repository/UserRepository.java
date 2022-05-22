package com.stc.security.repository;

import com.stc.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: job
 * Date: 16/04/22
 * Time: 15:27
 *
 * @author job
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
