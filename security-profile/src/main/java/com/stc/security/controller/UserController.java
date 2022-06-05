package com.stc.security.controller;

import com.stc.security.dto.UserDTO;
import com.stc.security.service.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * User: job
 * Date: 4/06/22
 * Time: 20:22
 *
 * @author job
 */
@RestController
public class UserController {

    private final ManagerUserService userService;

    @Autowired
    public UserController(ManagerUserService userService){
        this.userService = userService;
    }


    @PostMapping("/security/user/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.createUser(userDTO));
    }
}
