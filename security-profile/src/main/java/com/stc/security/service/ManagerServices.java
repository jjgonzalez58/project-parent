package com.stc.security.service;

import com.stc.security.dto.ServiceDTO;
import com.stc.security.model.AuthorizationService;
import com.stc.security.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: job
 * Date: 19/05/22
 * Time: 19:25
 *
 * @author job
 */
@Service
@Slf4j
public class ManagerServices {

    private final ModelMapper modelMapper;

    private final ServiceRepository serviceRepo;

    @Autowired
    public ManagerServices(ServiceRepository serviceRepository){
        this.modelMapper = new ModelMapper();
        this.serviceRepo = serviceRepository;
    }

    public ServiceDTO createService(ServiceDTO service){
        AuthorizationService serviceAuto = parserServiceFromDto(service);
        if (serviceAuto.validateAuthorizationService()){
            AuthorizationService saved = serviceRepo.save(serviceAuto);
            log.info("Registro guardado correcatamente {}",saved.getServiceId());
            service.setId(saved.getServiceId());
            service.setErrorCode(0);
            service.setErrorMessage("La información ha sido guardada correctamente");
            return service;
        }
        service.setErrorCode(1);
        service.setErrorMessage("Error Guardando enn BD");
        return service;
    }

    public List<ServiceDTO> loadAll(){
        List<AuthorizationService>  services = this.serviceRepo.findAll();
        List<ServiceDTO> dtos = new ArrayList<>();
        services.forEach(service -> {
            dtos.add(parseDtoFromService(service));
        });
        return dtos;
    }

    private AuthorizationService parserServiceFromDto(ServiceDTO  dto){
        return modelMapper.map(dto,AuthorizationService.class);
    }
    private ServiceDTO parseDtoFromService(AuthorizationService sservice){
        return modelMapper.map(sservice,ServiceDTO.class);
    }

}
