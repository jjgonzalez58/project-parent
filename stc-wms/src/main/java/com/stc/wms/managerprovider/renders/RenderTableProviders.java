package com.stc.wms.managerprovider.renders;

import com.stc.wms.managerprovider.dto.ProviderDTO;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class RenderTableProviders implements ListitemRenderer {
    @Override
    public void render(Listitem item, Object provider, int i) throws Exception {
        ProviderDTO providerDTO = (ProviderDTO) provider;
        Listcell id = new Listcell(String.valueOf(providerDTO.getIdProveedor()));
        Listcell nit = new Listcell(providerDTO.getNit());
        Listcell nombre = new Listcell(providerDTO.getNombre());
        Listcell direccion = new Listcell(providerDTO.getDireccion());
        Listcell telefono = new Listcell(providerDTO.getTelefono());
        Listcell contacto = new Listcell(providerDTO.getContacto());
        Listcell email = new Listcell(providerDTO.getEmail());
        Listcell status = new Listcell(providerDTO.getStatus());


        item.appendChild(id);
        item.appendChild(nit);
        item.appendChild(nombre);
        item.appendChild(direccion);
        item.appendChild(telefono);
        item.appendChild(contacto);
        item.appendChild(email);
        item.appendChild(status);

    }
}
