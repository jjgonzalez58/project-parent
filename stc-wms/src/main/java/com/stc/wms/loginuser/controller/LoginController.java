package com.stc.wms.loginuser.controller;

import com.stc.wms.loginuser.model.LoginRequest;
import com.stc.wms.loginuser.model.LoginResponse;
import com.stc.wms.loginuser.model.dto.ValidateUserRequest;
import com.stc.wms.loginuser.model.dto.ValidateUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: job
 * Date: 4/06/22
 * Time: 22:35
 *
 * @author job
 */
@FeignClient(name = "securityLoginUser", url="localhost:8083")
public interface LoginController {

    @PostMapping("/security/login/loginUser")
    ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest);

    @PostMapping("security/login/validateUser")
    ResponseEntity<ValidateUserResponse> validateUser(@RequestBody ValidateUserRequest request);
}
