package com.stc.security.model;

import lombok.Data;

import javax.persistence.*;

/**
 * User: job
 * Date: 9/05/22
 * Time: 20:04
 *
 * @author job
 */
@Data
@Entity
@Table(name = "rol", schema = "security")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrol;
    private String nombre;
    private String descripcion;
}
