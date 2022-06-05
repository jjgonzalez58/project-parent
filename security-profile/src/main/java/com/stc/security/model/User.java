package com.stc.security.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


/**
 * User: job
 * Date: 16/04/22
 * Time: 14:54
 *
 * @author job
 */
@Data
@Entity
@Table(name = "usuario", schema = "security")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarioid")
    private Integer userid;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellido")
    private String lastname;
    private String alias;
    private String password;
    private int rol_idrol;
    @ManyToMany
    @JoinTable(name = "asigna_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfilList;

    public boolean validUser() {
        boolean isValidUser =  true;
        if (this.name ==null || this.name.isEmpty())
            isValidUser = false;
        if (this.alias == null || this.alias.isEmpty())
            isValidUser = false;
        if (this.password == null || this.password.isEmpty())
            isValidUser = false;

        return isValidUser;
    }
}

