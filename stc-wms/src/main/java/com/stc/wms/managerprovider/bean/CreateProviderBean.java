package com.stc.wms.managerprovider.bean;

import com.stc.wms.managerprovider.dto.ProviderDTO;
import com.stc.wms.managerprovider.service.CreateProviderService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@Data
@Slf4j
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CreateProviderBean {
    private ProviderDTO providerDTO;
    private String errorMessage;
    @WireVariable("provider")
    private CreateProviderService service;
    @Init
    public void init(){
        varClear();
    }

    @Command
    @NotifyChange({"provider" ,"errorMessage"})
    public void savedProvider(){
        setErrorMessage(null);
        if(this.providerDTO.getNombre()==null || this.providerDTO.getNombre().isEmpty()) {
            log.error(" El nombre no  es valido");
            setErrorMessage("El nombre no  es valido");
            return;
        }
        if(this.providerDTO.getNit()==null || this.providerDTO.getNit().isEmpty()) {
            log.error("El nit no es valido");
            setErrorMessage("El nit no es valido");
            return;
        }
        if(this.providerDTO.getDireccion()==null || this.providerDTO.getDireccion().isEmpty()) {
            log.error("La direccion no es valida");
            setErrorMessage("La direccion no es valida");
            return;
        }
        if(this.providerDTO.getTelefono()==null || this.providerDTO.getTelefono().isEmpty()) {
            log.error("El telefono no es valido");
            setErrorMessage("El telefono no es valido");
            return;
        }
        if(this.providerDTO.getContacto()==null || this.providerDTO.getContacto().isEmpty()) {
            log.error("El contacto no es valido");
            setErrorMessage("El contancto no es valido");
            return;
        }
        if(this.providerDTO.getEmail()==null || this.providerDTO.getEmail().isEmpty()) {
            log.error("El Correo Electronico no es valido");
            setErrorMessage("El Correo Electronico no es valido");
            return;
        }
        if(this.providerDTO.getStatus()==null || this.providerDTO.getStatus().isEmpty()) {
            log.error("El estatus no es valido");
            setErrorMessage("El estatus no es valido");
            return;
        }
            this.service.SaveProvider(this.providerDTO);
            log.info("Proveedor Guardado con exito");
            this.providerDTO = new ProviderDTO();

    }

    public void varClear(){
        this.providerDTO = new ProviderDTO();
        this.errorMessage = null;
    }
}
