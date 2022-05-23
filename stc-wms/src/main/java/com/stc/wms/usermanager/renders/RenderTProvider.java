package com.stc.wms.usermanager.renders;

import com.stc.wms.managerprovider.dto.ProviderDTO;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import javax.swing.*;

public class RenderTProvider implements ListitemRenderer {
    @Override
    public void render(Listitem providers, Object provider, int i) throws Exception {
        Listcell nit = new Listcell(((ProviderDTO) provider).getNit());
        Listcell nombre = new Listcell(((ProviderDTO) provider).getNombre());
        Listcell direccion = new Listcell(((ProviderDTO) provider).getDireccion());
        Listcell telefono = new Listcell(((ProviderDTO) provider).getTelefono());
        Listcell contacto = new Listcell(((ProviderDTO) provider).getContacto());
        Listcell email = new Listcell(((ProviderDTO) provider).getEmail());
        Listcell status= new Listcell(((ProviderDTO) provider).getStatus());


        providers.appendChild(nit);
        providers.appendChild(nombre);
        providers.appendChild(direccion);
        providers.appendChild(telefono);
        providers.appendChild(contacto);
        providers.appendChild(email);
        providers.appendChild(contacto);
        providers.appendChild(status);

        providers.setAttribute("selected", provider);
    }
}
