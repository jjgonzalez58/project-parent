package com.stc.security.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * User: job
 * Date: 9/05/22
 * Time: 20:11
 *
 * @author job
 */
@Data
@Entity
@Table(name = "login_sesion", schema = "security")
public class LoginSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlogin_sesion")
    private int loginSesionId;
    @Column(name = "fecha_ingreso")
    private Timestamp entryDate;
    @Column(name = "fecha_final")
    private Timestamp exitDate;
    @Column(name = "estado")
    private String status;
    @Column(name = "usuario_id")
    private int usuarioId;
}
