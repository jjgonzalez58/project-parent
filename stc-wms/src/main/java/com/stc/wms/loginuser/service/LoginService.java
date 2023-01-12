package com.stc.wms.loginuser.service;

import com.stc.wms.loginuser.controller.LoginController;
import com.stc.wms.loginuser.model.LoginRequest;
import com.stc.wms.loginuser.model.LoginResponse;
import com.stc.wms.loginuser.model.dto.ValidateUserRequest;
import com.stc.wms.loginuser.model.dto.ValidateUserResponse;
import com.stc.wms.security.dto.UserDTO;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * User: job
 * Date: 7/05/22
 * Time: 21:31
 *
 * @author job
 */
@Service("loginService")
@Slf4j
public class LoginService {

    private final LoginController controller;

    @Autowired
    public LoginService(LoginController controller){
        this.controller = controller;
    }

    public LoginResponse login(LoginRequest request){
        log.info("request: "+request.toString());

        LoginResponse  response = new LoginResponse();
        response.setCodError(0);
        response.setMessageError("usuario Encontrado");
        UserDTO  user = new UserDTO();
        user.setName("Job");
        user.setLastName("Gonzalez");
        user.setAlias("jgonzalez");
        user.setPassword("prueba");
        response.setLoginUser(user);
        return response;
//        try{
//            ResponseEntity<LoginResponse> login = controller.loginUser(request);
//            return login.getBody();
//        }catch (RetryableException ex) {
//            log.error("Error en interface ",ex);
//            LoginResponse response = new LoginResponse();
//            response.setCodError(1);
//            response.setMessageError("Error obteniendo permisos de usuario");
//            return response;
//        }
    }
    public ValidateUserResponse validateUser(LoginRequest request){
        //sim
        ValidateUserResponse  response = new ValidateUserResponse();
        response.setErrorCode(0);
        response.setMessage("Usuario valido");
        return response;

//        ValidateUserRequest  validate =  new ValidateUserRequest();
//        validate.setPass(request.getLoginPass());
//        validate.setUser(request.getLoginUser());
//        return this.controller.validateUser(validate).getBody();
    }
}
