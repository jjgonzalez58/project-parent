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
    private int perfilId;
    @Column(name = "nombre")
    private String name;
    @Column(name = "descripcion")
    private String description;
    @ManyToMany
    @JoinTable(name = "Autorizacion",
            joinColumns = @JoinColumn(name = "perfil_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id"))
    private List<AuthorizationService> serviceList;
}
