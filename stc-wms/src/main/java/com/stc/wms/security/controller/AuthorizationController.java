package com.stc.wms.security.controller;

import com.stc.wms.security.dto.ServiceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * User: job
 * Date: 21/05/22
 * Time: 11:38
 *
 * @author job
 */
@FeignClient(name = "security-prifile", url="localhost:8083")
public interface AuthorizationController {

    @PostMapping("/security/authorization/save")
    ResponseEntity<ServiceDTO> saveService(@RequestBody ServiceDTO autorization);

    @GetMapping("/security/authorization/all")
    ResponseEntity<List<ServiceDTO>> loadAll();
}
