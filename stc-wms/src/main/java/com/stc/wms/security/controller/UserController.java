package com.stc.wms.security.controller;

import com.stc.wms.security.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: job
 * Date: 4/06/22
 * Time: 20:34
 *
 * @author job
 */
@FeignClient(name = "securityUserManager", url="localhost:8083")
public interface UserController {

    @PostMapping("/security/user/save")
    ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO);
}
