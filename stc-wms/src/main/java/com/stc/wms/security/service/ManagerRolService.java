package com.stc.wms.security.service;

import com.stc.wms.security.controller.RollControler;
import com.stc.wms.security.dto.ProfileDTO;
import com.stc.wms.security.dto.RolDto;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: job
 * Date: 4/06/22
 * Time: 15:52
 *
 * @author job
 */
@Slf4j
@Service("rolManagerService")
public class ManagerRolService {

    private final RollControler rollControler;

    @Autowired
    public ManagerRolService(RollControler rollControler){
        this.rollControler = rollControler;
    }

    public List<RolDto> loadAllRols(){
        try{
            ResponseEntity<List<RolDto>> load = this.rollControler.loadAllRol();
            return load.getBody();
        }catch (RetryableException ex){
            log.error("Error consumiendo servicio ",ex);
            return null;
        }
    }

}
