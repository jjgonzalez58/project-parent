package com.stc.security.service;

import com.stc.security.dto.RolDto;
import com.stc.security.model.Rol;
import com.stc.security.repository.RolRepository;
import com.stc.security.service.shared.ModelMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: job
 * Date: 28/05/22
 * Time: 07:34
 *
 * @author job
 */
@Service
@Slf4j
public class ManagerRolService {

    private final RolRepository repository;
    private final ModelMapperService mapperService;

    @Autowired
    public ManagerRolService(RolRepository repo,ModelMapperService modelMapperService){
        this.repository = repo;
        this.mapperService = modelMapperService;
    }

    public RolDto createRol(RolDto role){
        Rol  rolCreated = (Rol) mapperService.parserServiceFromDto(role,new Rol());
        if (rolCreated.validateData()){
            Rol rolsaved = this.repository.save(rolCreated);
            log.info(" Registro guardado correctamente {} ",rolsaved.getIdrol());
            role.setIdrol(rolsaved.getIdrol());
            role.setErrorCode(0);
            role.setErrorMessage("Informacion guardada correctamente");
            return role;
        }
        role.setErrorMessage("Error guardando informacion");
        role.setErrorCode(1);
        return role;
    }

    public List<RolDto> loadAllRole(){
        List<Rol> rols = this.repository.findAll();
        List<RolDto> rolDtos = new ArrayList<>();
        rols.forEach(role ->{
            rolDtos.add((RolDto) this.mapperService.parseDtoFromService(role,new RolDto()));
        });
        return rolDtos;
    }


}
