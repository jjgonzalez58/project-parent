package com.stc.security.repository;

import com.stc.security.model.AuthorizationService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: job
 * Date: 9/05/22
 * Time: 20:48
 *
 * @author job
 */
public interface ServiceRepository extends JpaRepository<AuthorizationService, Integer> {
}
