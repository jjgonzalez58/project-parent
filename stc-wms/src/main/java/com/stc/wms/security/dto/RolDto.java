package com.stc.wms.security.dto;

import lombok.Data;

/**
 * User: job
 * Date: 28/05/22
 * Time: 08:11
 *
 * @author job
 */
@Data
public class RolDto {
    private int errorCode;
    private String errorMessage;
    private int idrol;
    private String nombre;
    private String descripcion;
}
