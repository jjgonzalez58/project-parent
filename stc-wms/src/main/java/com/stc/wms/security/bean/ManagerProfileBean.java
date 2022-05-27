package com.stc.wms.security.bean;

import com.stc.wms.security.dto.ProfileDTO;
import com.stc.wms.security.dto.ServiceDTO;
import com.stc.wms.security.service.ManagerAuthorizationService;
import com.stc.wms.security.service.ManagerProfileService;
import com.stc.wms.shared.bean.GeneralTransactionBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    private ProfileDTO createProfile;
    private ProfileDTO currentProfile;
    private List<ProfileDTO> listProfile;
    private List<ServiceDTO> listServices;
    private List<ServiceDTO> tmpListService;
    private ServiceDTO selectLeftService;
    private ServiceDTO selectRigthService;
    private boolean editMode;


    @WireVariable("profileManager")
    private ManagerProfileService profileService;
    @WireVariable("authorizationService")
    private ManagerAuthorizationService services;

    @Init
    public void init(){
        this.createProfile = new ProfileDTO();
        setEditMode(false);
        loadProfileList();
        this.listServices = services.loadAllService();
    }

    @Command
    @NotifyChange({"listProfile","createProfile"})
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
            showSuccessMessageBox(prfSave.getErrorMessage());
        }else {
            showErrorMessageBox(prfSave.getErrorMessage());
        }
        this.createProfile = new ProfileDTO();
        this.listServices = services.loadAllService();
    }
    @Command
    public void editProfile(){}

    @Command
    public void newProfile(){}
    @Command
    public void deleteProfile(){}

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
