package com.stc.security.controller;

import com.stc.security.dto.LoginRequest;
import com.stc.security.dto.LoginResponse;
import com.stc.security.dto.ValidateUserRequest;
import com.stc.security.dto.ValidateUserResponse;
import com.stc.security.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: job
 * Date: 4/06/22
 * Time: 22:44
 *
 * @author job
 */
@RestController
public class LoginController {

    private final LoginUserService service;

    @Autowired
    public LoginController(LoginUserService userService){
        this.service = userService;
    }

    @PostMapping("/security/login/loginUser")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(this.service.loginUser(loginRequest));
    }
    @PostMapping("security/login/validateUser")
    public ResponseEntity<ValidateUserResponse> validateUser(@RequestBody ValidateUserRequest request){
        return ResponseEntity.ok(this.service.validateUser(request));
    }
}
