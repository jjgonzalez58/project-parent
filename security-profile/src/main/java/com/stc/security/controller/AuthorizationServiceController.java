package com.stc.security.controller;

import com.stc.security.dto.ServiceDTO;
import com.stc.security.repository.ServiceRepository;
import com.stc.security.service.ManagerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: job
 * Date: 21/05/22
 * Time: 11:22
 *
 * @author job
 */
@RestController
public class AuthorizationServiceController {

    private final ManagerServices managerServices;

    @Autowired
    public AuthorizationServiceController(ManagerServices mservice){
        this.managerServices = mservice;
    }

    @PostMapping("/security/authorization/save")
    public ResponseEntity<ServiceDTO> saveService(@RequestBody ServiceDTO autorization){
        return ResponseEntity.ok(managerServices.createService(autorization));
    }
    @GetMapping("/security/authorization/all")
    ResponseEntity<List<ServiceDTO>> loadAll(){
        return ResponseEntity.ok(managerServices.loadAll());
    }
}
