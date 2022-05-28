package com.stc.wms.security.controller;

import com.stc.wms.security.dto.RolDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * User: job
 * Date: 28/05/22
 * Time: 08:07
 *
 * @author job
 */
@FeignClient(name = "securityRoller", url="localhost:8083")
public interface RollControler {
    @PostMapping("/security/roll/save")
    public ResponseEntity<RolDto> saveRoll(@RequestBody RolDto rolldto);
    @GetMapping("/security/roll/all")
    ResponseEntity<List<RolDto>> loadAllRol();
}
