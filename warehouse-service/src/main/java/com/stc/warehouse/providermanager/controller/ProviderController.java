package com.stc.warehouse.providermanager.controller;

import com.stc.warehouse.dtos.ProviderDTO;
import com.stc.warehouse.entities.Provider;
import com.stc.warehouse.providermanager.services.CreateProviderService;
import com.stc.warehouse.providermanager.services.FindProvidersService;
import com.stc.warehouse.repositories.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ProviderController {

    private final ProviderRepository providerRepository;
    private final CreateProviderService createProviderService;
    private final FindProvidersService findProvidersService;

    private  final ModelMapper modelMapper;

    @Autowired
    public ProviderController(ProviderRepository providerRepository, CreateProviderService createProviderService, FindProvidersService findProvidersService, ModelMapper modelMapper) {
        this.providerRepository = providerRepository;
        this.createProviderService = createProviderService;
        this.findProvidersService = findProvidersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/providers/list")
    public List<ProviderDTO> findAllProviders(){
        List<ProviderDTO>  providerDTOS=  findProvidersService.findAllProviders()
                .stream()
                .map(provider -> modelMapper
                        .map(provider, ProviderDTO.class))
                        .collect(Collectors.toList());
        log.info("LISTADO DE DTOS DE PROVEEDORES"+ providerDTOS);
        return providerDTOS;
    }
}