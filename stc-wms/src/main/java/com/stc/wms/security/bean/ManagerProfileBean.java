package com.stc.wms.security.bean;

import com.stc.wms.security.dto.ProfileDTO;
import com.stc.wms.security.dto.ServiceDTO;
import com.stc.wms.security.service.ManagerAuthorizationService;
import com.stc.wms.security.service.ManagerProfileService;
import com.stc.wms.shared.bean.GeneralTransactionBean;
import com.stc.wms.shared.bean.SharedExecution;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * User: job
 * Date: 21/05/22
 * Time: 23:29
 *
 * @author job
 */
@Slf4j
@Data
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ManagerProfileBean extends GeneralTransactionBean {

    private Desktop desktop;
    private ProfileDTO createProfile;
    private ProfileDTO currentProfile;
    private List<ProfileDTO> listProfile;
    private List<ServiceDTO> listServices;
    private List<ServiceDTO> tmpListService;
    private ServiceDTO selectLeftService;
    private ServiceDTO selectRigthService;
    private boolean editMode;
    private boolean embbedeMode;


    @WireVariable("profileManager")
    private ManagerProfileService profileService;
    @WireVariable("authorizationService")
    private ManagerAuthorizationService services;

    @Init
    public void init(@ContextParam(ContextType.DESKTOP)Desktop desktop){
        this.desktop = desktop;
        Map arg = Executions.getCurrent().getArg();
        this.listServices = services.loadAllService();
        if ( arg != null && !arg.isEmpty()){
            log.info("trae parametros***");
            this.createProfile = (ProfileDTO) arg.get(SharedExecution.WINDOWENBEDED.getValue());
            if (this.createProfile !=null) {
                this.tmpListService = createProfile.getServiceList();
                sortList(this.tmpListService);
                filterProfileList();
                sortList(this.listServices);
                setEditMode(true);
                setEmbbedeMode(true);
            }
        }else {
            this.createProfile = new ProfileDTO();
            setEditMode(false);
            setEmbbedeMode(false);
            loadProfileList();
        }
    }

    @Command
    @NotifyChange({"listProfile","createProfile","listProfile"})
    public void saved(){
        if (this.createProfile == null){
            showErrorMessageBox("Debe ingresar informacion general del perfil");
            return;
        }
        if (this.createProfile.getName() == null || this.createProfile.getName().isEmpty()){
            showErrorMessageBox("Debe ingresar un nombre");
            return;
        }
        if (this.createProfile.getDescription() == null || this.createProfile.getDescription().isEmpty()){
            showErrorMessageBox("Ingrese una descripcion del perfil");
            return;
        }
        if (this.tmpListService != null && !this.tmpListService.isEmpty()){
            this.createProfile.setServiceList(this.tmpListService);
        }
        ProfileDTO prfSave = this.profileService.savedProfile(this.createProfile);
        if (prfSave.getErrorCode() == 0){
            log.info("Objeto guardado correctamente "+prfSave.getErrorMessage());
            super.showSuccessMessageBox(prfSave.getErrorMessage());
        }else {
            super.showErrorMessageBox(prfSave.getErrorMessage());
        }
        this.createProfile = new ProfileDTO();
        this.listServices = services.loadAllService();
        loadProfileList();
    }
    @Command
    public void editProfile(){}

    @Command
    public void newProfile(){}
    @Command
    public void deleteProfile(){}
    @Command
    public void search(){}

    @Command
    @NotifyChange({"listServices","tmpListService"})
    public void asignedService(){
        if (this.selectLeftService != null){
            if (this.tmpListService == null)
                this.tmpListService = new ArrayList<>();

            if (!this.tmpListService.contains(this.selectLeftService)) {
                this.tmpListService.add(this.selectLeftService);
                this.listServices.remove(this.selectLeftService);
            }
            sortList(this.tmpListService);
        }

    }
    @Command
    @NotifyChange({"listServices","tmpListService"})
    public void asignedAll(){
            if (this.tmpListService == null)
                this.tmpListService = new ArrayList<>();

            this.tmpListService.addAll(this.listServices);
            this.listServices.clear();
        sortList(this.tmpListService);

    }
    @Command
    @NotifyChange({"listServices","tmpListService"})
    public void deleteService(){
        if (this.selectRigthService != null){
            if (this.listServices == null)
                this.listServices = new ArrayList<>();

            if (!this.listServices.contains(this.selectRigthService)) {
                this.listServices.add(this.selectRigthService);
                this.tmpListService.remove(this.selectRigthService);
            }
            sortList(this.listServices);
        }

    }
    @Command
    @NotifyChange({"listServices","tmpListService"})
    public void deleteAll(){
            if (this.listServices == null)
                this.listServices = new ArrayList<>();

            this.listServices.addAll(this.tmpListService);
            this.tmpListService.clear();
            sortList(this.listServices);
    }
    private void filterProfileList(){
        if (this.tmpListService !=null && !this.tmpListService.isEmpty()){
            this.tmpListService.forEach(prf ->{
                this.listServices.remove(prf);
            });
        }
    }
    public void loadProfileList(){
        this.listProfile = profileService.loadAllProfile();
    }

    /** Método para ordenar una lista de servicios
     * @param list arreglo de Objetos ServiceDTO
     * */
    public void sortList(List<ServiceDTO> list){
        list.sort(Comparator.comparingInt(ServiceDTO::getId));
    }

}
