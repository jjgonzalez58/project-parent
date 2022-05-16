package com.stc.wms.usermanager.controller;

import com.stc.wms.usermanager.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: job
 * Date: 9/05/22
 * Time: 22:45
 *
 * @author job
 */
@FeignClient(name = "${security.service.name}", url = "${security.service.url}")
public interface CreateUserController {

    @PostMapping("/user/save")
    public ResponseEntity<Object> savedUser(@RequestBody UserDTO user);

}
