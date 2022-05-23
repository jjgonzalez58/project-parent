package com.stc.wms.security.bean;

import com.stc.wms.security.dto.ProfileDTO;
import com.stc.wms.security.service.ManagerProfileService;
import com.stc.wms.shared.bean.GeneralTransactionBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.ArrayList;
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
    private boolean editMode;


    @WireVariable("profileManager")
    private ManagerProfileService profileService;

    @Init
    public void init(){
        this.createProfile = new ProfileDTO();
        setEditMode(false);
        loadProfileList();
    }

    @Command
    public void saved(){

    }
    @Command
    public void editProfile(){}

    @Command
    public void newProfile(){}
    @Command
    public void deleteProfile(){}

    public void loadProfileList(){
        this.listProfile = profileService.loadAllProfile();
    }

}
