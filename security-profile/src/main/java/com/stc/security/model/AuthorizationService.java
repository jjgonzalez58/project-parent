package com.stc.security.model;

import lombok.Data;

import javax.persistence.*;

/**
 * User: job
 * Date: 9/05/22
 * Time: 20:26
 *
 * @author job
 */
@Data
@Entity
@Table(name = "servicio", schema = "security")
public class AuthorizationService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservicio")
    private int serviceId;
    @Column(name = "codigo")
    private String code;
    @Column(name = "descripcion")
    private String description;

    public boolean validateAuthorizationService(){
        if (this.code == null || this.code.isEmpty())
            return false;
        return this.description != null && !this.description.isEmpty();
    }
}
