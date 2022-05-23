package com.stc.warehouse.providermanager.services;
import com.stc.warehouse.entities.Provider;
import com.stc.warehouse.repositories.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindProvidersService {
    private  final ProviderRepository providerRepository;

    @Autowired
    public FindProvidersService(ProviderRepository providerRepository){
        this.providerRepository = providerRepository;
    }
    public List<Provider> findAllProviders(){
        List<Provider> providers = this.providerRepository.findAll();
        if(providers.isEmpty()){
            log.info("no se encontraron proveedores");
            return providers;
        }
            log.info(" se encontraron proveedores");
             return  providers;
    }
}