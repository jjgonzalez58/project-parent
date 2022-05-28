package com.stc.warehouse.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDTO {
    private Integer idproveedor;
    private String nit;
    private String nombre;
    private String direccion;
    private String telefono;
    private String contacto;
    private String email;
    private String status;
}