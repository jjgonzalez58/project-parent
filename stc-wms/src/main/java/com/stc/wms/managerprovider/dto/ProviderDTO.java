package com.stc.wms.managerprovider.dto;

import lombok.Data;

@Data
public class ProviderDTO {
    private Integer idProveedor;
    private String nit;
    private String nombre;
    private String direccion;
    private String telefono;
    private String contacto;
    private String email;
    private String status;
}
