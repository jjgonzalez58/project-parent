package com.stc.security.repository;

import com.stc.security.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: job
 * Date: 9/05/22
 * Time: 20:49
 *
 * @author job
 */
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
}
