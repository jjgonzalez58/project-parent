package com.stc.wms.managerprovider.service;

import com.stc.wms.managerprovider.controller.ProviderController;
import com.stc.wms.managerprovider.dto.ProviderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// IMPORTANTE
@Slf4j
@Service("provider")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CreateProviderService {
    public final ProviderController controller;

    @Autowired
    public CreateProviderService(ProviderController controller) {
        this.controller = controller;
    }
    public boolean SaveProvider(ProviderDTO providerDTO){
        log.info("provider {}", providerDTO);
        ResponseEntity<Object> response = controller.createProvider(providerDTO);
        return (boolean) response.getBody();
    }
}
