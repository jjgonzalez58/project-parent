package com.stc.security.service;

import com.stc.security.dto.UserDTO;
import com.stc.security.model.User;
import com.stc.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: job
 * Date: 4/06/22
 * Time: 20:24
 *
 * @author job
 */
@Service
@Slf4j
public class ManagerUserService {

    private final UserRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public ManagerUserService(UserRepository repository){
        this.repository = repository;
        this.modelMapper = new ModelMapper();
    }
    public UserDTO createUser(UserDTO dto){
        User  user =  parseUserFromDto(dto);
        if (user.validUser()){
            User userSave = this.repository.save(user);
            dto.setUserid(userSave.getUserid());
            dto.setErrorCode(0);
            dto.setErrorMessage("Usuario creado correctamente");
        }else {
            dto.setErrorCode(1);
            dto.setErrorMessage("Error guardando registro");
        }
        return dto;
    }


    public User parseUserFromDto(UserDTO dto){
        return modelMapper.map(dto, User.class);
    }
    public UserDTO parseDtoFromUser(User user){
        return modelMapper.map(user,UserDTO.class);
    }

}
