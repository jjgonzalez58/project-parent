package com.stc.warehouse.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proveedor")

public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproveedor;
    private String nit;
    private String nombre;
    private String direccion;
    private String telefono;
    private String contacto;
    private String email;
    private String status;

    //lo estipulamos como tipo EAGER,que todos los datos se actualizaran en cascada y que este se encuentra
    //mapeado al un atributo del tipo de la entidad llamado proveedor
    //este tipo de variable nos sirve para poder traer de la base de datos todas las ordenes de compra con un tipo de
    //proveedor espefico
    //espeficamos que hay un proveedor para muchas ordenes
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "proveedorId")
    //private List<OrdenCompra> ordenCompras;

    public boolean validProvider() {
        boolean isValid = true;

        if (this.nit == null || this.nit.isEmpty())
            isValid = false;
        if (this.nombre == null || this.nombre.isEmpty())
            isValid = false;
        if(this.direccion==null || this.direccion.isEmpty())
            isValid=false;
        if (this.telefono==null || this.telefono.isEmpty())
            isValid=false;
        if(this.contacto==null || this.contacto.isEmpty())
            isValid=false;
        if(this.email==null || this.email.isEmpty())
            isValid=false;
        if (this.status==null || this.status.isEmpty())
            isValid=false;

        return isValid;
    }
}