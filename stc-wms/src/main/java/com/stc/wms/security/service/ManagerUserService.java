package com.stc.wms.security.service;

import com.stc.wms.security.controller.UserController;
import com.stc.wms.security.dto.UserDTO;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * User: job
 * Date: 4/06/22
 * Time: 20:36
 *
 * @author job
 */
@Service("managerUserService")
@Slf4j
public class ManagerUserService {

    private final UserController controller;

    @Autowired
    public ManagerUserService(UserController controller){
        this.controller = controller;
    }

    public UserDTO savedUser(UserDTO user){
        try {
            ResponseEntity<UserDTO> save = this.controller.saveUser(user);
            return Objects.requireNonNull(save.getBody());
        }catch (RetryableException ex){
            log.error("Error consumiengo servicio cliente",ex);
            UserDTO errorReturned = new UserDTO();
            errorReturned.setErrorCode(1);
            errorReturned.setErrorMessage("Error almacenando la informacion");
            return errorReturned;
        }
    }
}
