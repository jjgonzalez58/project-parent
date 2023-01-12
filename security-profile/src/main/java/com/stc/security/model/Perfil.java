package com.stc.security.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * User: job
 * Date: 9/05/22
 * Time: 20:22
 *
 * @author job
 */
@Data
@Entity
@Table(name = "perfil", schema = "security")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfilid")
    private int perfilId;
    @Column(name = "nombre")
    private String name;
    @Column(name = "descripcion")
    private String description;
    @ManyToMany
    @JoinTable(name = "Autorizacion",
            joinColumns = @JoinColumn(name = "perfilid"),
            inverseJoinColumns = @JoinColumn(name = "idservicio"))
    private List<AuthorizationService> serviceList;

    public boolean validateData(){
        if (this.name == null || this.name.isEmpty())
            return false;
        return this.description != null && !this.description.isEmpty();
    }
}
