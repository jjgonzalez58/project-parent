package com.stc.wms.security.bean;

import com.stc.wms.security.dto.ServiceDTO;
import com.stc.wms.security.service.ManagerAuthorizationService;
import com.stc.wms.shared.bean.GeneralTransactionBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * User: job
 * Date: 21/05/22
 * Time: 08:38
 *
 * @author job
 */
@Slf4j
@Data
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ManagerServiceBean extends GeneralTransactionBean {
    private ServiceDTO createService;
    private List<ServiceDTO> listService;
    private ServiceDTO currentService;
    private boolean editMode;

    @WireVariable("authorizationService")
    private ManagerAuthorizationService autoService;

    @Init
    public void init(){
        createService = new ServiceDTO();
        listService = new ArrayList<>();
        setEditMode(false);
        loadAllService();
    }

    @Command
    @NotifyChange({"createService","editMode"})
    public void newService(){
        setEditMode(false);
        this.createService = new ServiceDTO();
    }


    @Command
    @NotifyChange({"createService","listService","editMode"})
    public void saved(){
        if (validDataServcie()){
           ServiceDTO  sav =  this.autoService.savedService(this.createService);
           if (sav.getErrorCode() == 0){
                newService();
                loadAllService();
                setEditMode(false);
               showSuccessMessageBox(sav.getErrorMessage());
           }else {
               showErrorMessageBox(sav.getErrorMessage());
           }
        }
    }

    @Command
    @NotifyChange({"createService","listService","editMode"})
    public void editService(@BindingParam("selected") ServiceDTO select){
        this.createService = select;
        setEditMode(true);
    }
    @Command
    @NotifyChange({"createService","listService","editMode"})
    public void deleteService(@BindingParam("selected") ServiceDTO select){
        this.currentService = select;
        showConfirmMessageBox("Confirma que desea Eliminar el servicio ["+
                this.currentService.getCode()+"]",this);
    }
    @Override
    public void doAccepConfirm() {
        log.info("Selecciono  eliminar ***** ");
    }

    private boolean validDataServcie(){
        if (this.createService.getCode() == null ||
                this.createService.getCode().isEmpty()){
           showErrorMessageBox("Debe ingresar un código de servicio");
            return false;
        }
        if (this.createService.getDescription() == null ||
                this.createService.getDescription().isEmpty()){
            showErrorMessageBox("Debe ingresar na descripción");
            return false;
        }
        return true;
    }

    private void loadAllService(){
        this. listService = this.autoService.loadAllService();
    }
}
