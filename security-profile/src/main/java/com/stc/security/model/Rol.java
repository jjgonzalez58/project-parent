package com.stc.security.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

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

    public boolean validateData(){
        boolean validate = this.nombre != null && !this.nombre.isEmpty();
        if (this.descripcion == null || this.descripcion.isEmpty())
            validate = false;
        return validate;
    }
}
