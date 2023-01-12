package com.stc.security.service;

import com.stc.security.dto.*;
import com.stc.security.model.User;
import com.stc.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * User: job
 * Date: 4/06/22
 * Time: 22:49
 *
 * @author job
 */
@Service
@Slf4j
public class LoginUserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LoginUserService(UserRepository repository){
        this.userRepository = repository;
        this.modelMapper = new ModelMapper();
    }

    public LoginResponse loginUser(LoginRequest request){
        LoginResponse  response = new LoginResponse();
        log.info("Request "+request.toString());

//        LoginResponse login = new LoginResponse();
        response.setCodError(0);
        response.setMessageError("");
        UserDTO  use = new UserDTO();
        use.setName("Job");
        use.setLastName("Gonzalez");
        use.setAlias("jgonzalez");
        use.setErrorCode(0);
        response.setLoginUser(use);
        return response;

//        User loginUser = this.userRepository.findUserByAliasAndPass(request.getLoginUser(), request.getLoginPass());
//        if (loginUser != null){
//            response.setCodError(0);
//            response.setMessageError("Usuario obtenido");
//            response.setLoginUser(parseDtoFromUser(loginUser));
//        }else{
//            response.setCodError(1);
//            response.setMessageError("No se encontro el usuario");
//        }
//        return response;
    }
    public ValidateUserResponse validateUser(ValidateUserRequest request){
        log.info("usuario recibido "+request.getUser());
        ValidateUserResponse response = new ValidateUserResponse();
        response.setErrorCode(0);
        response.setMessage("Usuario no Encontrado");
        return response;
    }

    public UserDTO parseDtoFromUser(User user){
        return modelMapper.map(user,UserDTO.class);
    }
}
