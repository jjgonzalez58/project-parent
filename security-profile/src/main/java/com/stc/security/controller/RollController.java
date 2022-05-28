package com.stc.security.controller;

import com.stc.security.dto.RolDto;
import com.stc.security.service.ManagerRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: job
 * Date: 28/05/22
 * Time: 08:01
 *
 * @author job
 */
@RestController
public class RollController {

    private final ManagerRolService rolService;

    @Autowired
    public RollController(ManagerRolService rolService){
        this.rolService = rolService;
    }

    @PostMapping("/security/roll/save")
    public ResponseEntity<RolDto> saveRoll(@RequestBody RolDto rolldto){
        return ResponseEntity.ok(rolService.createRol(rolldto));
    }
    @GetMapping("/security/roll/all")
    ResponseEntity<List<RolDto>> loadAllRol(){
        return ResponseEntity.ok(rolService.loadAllRole());
    }
}
