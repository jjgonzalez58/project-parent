package com.stc.wms.managerprovider.bean;

import com.stc.wms.managerprovider.dto.ProviderDTO;
import com.stc.wms.managerprovider.renders.RenderTableProviders;
import com.stc.wms.managerprovider.service.FindProviderService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.*;

import java.util.List;
@Data
@Slf4j
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewTableProviderBean {
    private List<ProviderDTO> providers;
    private String errorMessage;
    private ListModelList<ProviderDTO> model;
    private Listbox tableProviders;

    private  RenderTableProviders render;

    @WireVariable("findProvider")
    private FindProviderService findProviderService;


    @Init
    public void init(){
        loadTable();
    }
    @Command
    @NotifyChange({"tableProviders" ,"errorMessage"})
    public ListModelList<ProviderDTO> loadTable(){
        this.model =  new ListModelList<>(this.findProviderService.providerList());
         this.render = new RenderTableProviders();
        if(model==null){
            setErrorMessage("No se cargaron los proveedores");
            log.error("No hay proveedores registrados");
            return this.model;
        }
        setErrorMessage("Se cargaron los proveedores");
        log.error("Se cargan los proveedores registrados");

        return this.model;
    }
    public void onSelect$tableProviders(){
        ProviderDTO providerSelect = (ProviderDTO) tableProviders.getSelectedItem().getAttribute("selected");
    }
}

