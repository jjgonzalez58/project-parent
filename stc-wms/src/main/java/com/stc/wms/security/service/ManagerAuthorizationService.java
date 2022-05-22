package com.stc.wms.security.service;

import com.stc.wms.security.controller.AuthorizationController;
import com.stc.wms.security.dto.ServiceDTO;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * User: job
 * Date: 21/05/22
 * Time: 11:20
 *
 * @author job
 */
@Slf4j
@Service("authorizationService")
public class ManagerAuthorizationService {

    private final AuthorizationController authorizationController;
    public ManagerAuthorizationService(AuthorizationController autoController){
        this.authorizationController = autoController;
    }

    public ServiceDTO savedService(ServiceDTO service){
        try {
            ResponseEntity<ServiceDTO> save = this.authorizationController.saveService(service);
            return Objects.requireNonNull(save.getBody());
        }catch (RetryableException ex){
            log.error("Error consumiengo servicio cliente {}",ex);
            ServiceDTO errorReturned = new ServiceDTO();
            errorReturned.setErrorCode(1);
            errorReturned.setErrorMessage("Error almacenando la informacion");
            return errorReturned;
        }
    }

    public List<ServiceDTO> loadAllService(){
        try{
            ResponseEntity<List<ServiceDTO>> load = this.authorizationController.loadAll();
            return load.getBody();
        }catch (RetryableException ex){
            log.error("Error consumiendo servicio ",ex);
            return null;
        }
    }
}
