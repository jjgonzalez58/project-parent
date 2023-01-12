package com.stc.wms.security.service;

import com.stc.wms.security.controller.ProfileController;
import com.stc.wms.security.dto.ProfileDTO;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * User: job
 * Date: 21/05/22
 * Time: 23:18
 *
 * @author job
 */
@Slf4j
@Service("profileManager")
public class ManagerProfileService {

    private final ProfileController profileController;

    @Autowired
    public ManagerProfileService(ProfileController controller){
        this.profileController = controller;
    }

    public ProfileDTO savedProfile(ProfileDTO service){
        try {
            ResponseEntity<ProfileDTO> save = this.profileController.saveProfile(service);
            return Objects.requireNonNull(save.getBody());
        }catch (RetryableException ex){
            log.error("Error consumiengo servicio cliente",ex);
            ProfileDTO errorReturned = new ProfileDTO();
            errorReturned.setErrorCode(1);
            errorReturned.setErrorMessage("Error almacenando la informacion");
            return errorReturned;
        }catch (FeignException e){
            log.error("Error obteniendo datos",e);
            ProfileDTO errorReturned = new ProfileDTO();
            errorReturned.setErrorCode(e.status());
            if (e.status() == 500)
                errorReturned.setErrorMessage("Error interno en cliente");
            else
                errorReturned.setErrorMessage("Error almacenando la informacion");
            return errorReturned;
        }
    }

    public List<ProfileDTO> loadAllProfile(){
        try{
            ResponseEntity<List<ProfileDTO>> load = this.profileController.loadAllProfile();
            return load.getBody();
        }catch (RetryableException ex){
            log.error("Error consumiendo servicio ",ex);
            return null;
        }
    }


}
