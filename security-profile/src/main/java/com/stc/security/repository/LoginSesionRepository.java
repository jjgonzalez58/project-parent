package com.stc.security.repository;

import com.stc.security.model.LoginSesion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: job
 * Date: 9/05/22
 * Time: 20:51
 *
 * @author job
 */
public interface LoginSesionRepository extends JpaRepository<LoginSesion, Integer> {
}
