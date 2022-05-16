package com.stc.wms.usermanager.service;

import com.stc.wms.usermanager.controller.CreateUserController;
import com.stc.wms.usermanager.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * User: job
 * Date: 9/05/22
 * Time: 22:41
 *
 * @author job
 */
@Service("createUserService")
@Scope(value = "singleton",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CreateUserService {

    private final CreateUserController controller;

    @Autowired
    public CreateUserService(CreateUserController controller){
        this.controller = controller;
    }

    public boolean saveUser(UserDTO user){
       ResponseEntity<Object>  response = controller.savedUser(user);
       return (boolean) response.getBody();
    }
}
