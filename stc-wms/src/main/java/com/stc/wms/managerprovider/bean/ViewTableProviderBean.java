package com.stc.wms.managerprovider.bean;

import com.stc.wms.managerprovider.dto.ProviderDTO;
import com.stc.wms.managerprovider.service.FindProviderService;
import com.stc.wms.usermanager.renders.RenderTProvider;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import java.util.List;
@Data
@Slf4j
public class ViewTableProviderBean {
    private List<ProviderDTO> providers;
    private String errorMessage;
     private Listbox tableProviders;
    @WireVariable
    private FindProviderService findProviderService;

    @Init
    public void init(){
        loadTable();
    }
    @Command
    @NotifyChange({"tableProviders" ,"errorMessage"})
    public void loadTable(){
        this.providers = findProviderService.providerList();

        if(this.providers==null || this.providers.isEmpty()){
            setErrorMessage("No se cargaron los proveedores");
            log.error("No hay proveedores registrados");
        }else{
            ListModel model = new ListModelList<ProviderDTO>(providers);
            tableProviders.setModel(model);
            tableProviders.setItemRenderer(new RenderTProvider());
        }
    }
    public void onSelect$tableProviders(){
        ProviderDTO providerSelect = (ProviderDTO) tableProviders.getSelectedItem().getAttribute("selected");
    }
}
