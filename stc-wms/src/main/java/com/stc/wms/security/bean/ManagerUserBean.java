package com.stc.wms.security.bean;

import com.stc.wms.security.dto.ProfileDTO;
import com.stc.wms.security.dto.RolDto;
import com.stc.wms.security.dto.UserDTO;
import com.stc.wms.security.service.ManagerProfileService;
import com.stc.wms.security.service.ManagerRolService;
import com.stc.wms.security.service.ManagerUserService;
import com.stc.wms.shared.bean.GeneralTransactionBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * User: job
 * Date: 4/06/22
 * Time: 14:08
 *
 * @author job
 */
@Slf4j
@Data
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ManagerUserBean extends GeneralTransactionBean {
    private UserDTO createUser;
    private List<ProfileDTO> profileList;
    private List<ProfileDTO> selectListProfiles;
    private List<RolDto> listRols;
    private ProfileDTO selectRigthProfile;
    private ProfileDTO selectLeftProfile;
    private RolDto currentRolSelect;
    private boolean editMode;


    @WireVariable("profileManager")
    private ManagerProfileService profileService;
    @WireVariable("rolManagerService")
    private ManagerRolService rolService;
    @WireVariable("managerUserService")
    private ManagerUserService userService;

    @Init
    public void init(){
        this.createUser = new UserDTO();
        loadProfile();
        if (this.profileList != null && !this.profileList.isEmpty())
            sortListProfile(this.profileList);
        setEditMode(false);
        loadRols();
    }
    @Command
    @NotifyChange({"createUser","profileList","selectListProfiles"})
    public void saved(){
        if (this.createUser == null){
            super.showErrorMessageBox("Debe ingresar información para nuevo usuario");
            return;
        }
        if (isEmpty(this.createUser.getName())){
            super.showErrorMessageBox("debe Ingresar un nombre de usuario");
            return;
        }
        if (isEmpty(this.createUser.getLastName())){
            super.showErrorMessageBox("Ingrese un apellido para el usuario");
            return;
        }
        if (isEmpty(this.createUser.getAlias())){
            super.showErrorMessageBox("Seleccione un Alias");
            return;
        }
        if (isEmpty(this.createUser.getPassword())){
            super.showErrorMessageBox("seleccione una clave de usuario");
            return;
        }
        if (this.selectListProfiles != null &&  !this.selectListProfiles.isEmpty()){
            log.info("Perfiles "+this.selectListProfiles.size());
            this.createUser.setPerfilList(this.selectListProfiles);
        }
        if (this.currentRolSelect != null){
            this.createUser.setRol_idrol(this.currentRolSelect.getIdrol());
        }
        UserDTO savedUser = this.userService.savedUser(this.createUser);
        if (savedUser.getErrorCode() == 0){
            this.selectListProfiles.clear();
            this.createUser = new UserDTO();
            loadProfile();
            sortListProfile(this.profileList);
            super.showSuccessMessageBox(savedUser.getErrorMessage());
        }else{
            log.error("error creando usuario "+savedUser.getErrorMessage());
            super.showErrorMessageBox(savedUser.getErrorMessage());
        }

    }
    @Command
    public void newUser(){}
    @Command
    public void rolAssignUser(){
        log.info("Rol selected ");
    }
    @Command
    public void editProfile(@BindingParam("selected") ProfileDTO select){
        super.showWindow("~./profile/manager-profile.zul","Gestión de Perfiles",select);
    }

    @Command
    @NotifyChange({"profileList","selectListProfiles"})
    public void asignedAll(){
        if (this.selectListProfiles == null)
            this.selectListProfiles = new ArrayList<>();

        this.selectListProfiles.addAll(this.profileList);
        this.profileList.clear();
        sortListProfile(this.selectListProfiles);

    }
    @Command
    @NotifyChange({"profileList","selectListProfiles"})
    public void deleteAll(){
        if (this.profileList == null)
            this.profileList = new ArrayList<>();

        this.profileList.addAll(this.selectListProfiles);
        this.selectListProfiles.clear();
        sortListProfile(this.profileList);
    }
    @Command
    @NotifyChange({"profileList","selectListProfiles"})
    public void asignedProfile(){
        if (this.selectLeftProfile != null){
            if (this.selectListProfiles == null)
                this.selectListProfiles = new ArrayList<>();

            if (!this.selectListProfiles.contains(this.selectLeftProfile)) {
                this.selectListProfiles.add(this.selectLeftProfile);
                this.profileList.remove(this.selectLeftProfile);
            }
            sortListProfile(this.selectListProfiles);
        }

    }
    @Command
    @NotifyChange({"profileList","selectListProfiles"})
    public void deleteProfile(){
        if (this.selectRigthProfile != null){
            if (this.profileList == null)
                this.profileList = new ArrayList<>();

            if (!this.profileList.contains(this.selectRigthProfile)) {
                this.profileList.add(this.selectRigthProfile);
                this.selectListProfiles.remove(this.selectRigthProfile);
            }
            sortListProfile(this.profileList);
        }

    }

    private void loadProfile(){
        this.profileList = this.profileService.loadAllProfile();
    }
    private void loadRols(){
        this.listRols = this.rolService.loadAllRols();
    }
    private void sortListProfile(List<ProfileDTO> list){
        list.sort(Comparator.comparingInt(ProfileDTO::getPerfilId));
    }
}
