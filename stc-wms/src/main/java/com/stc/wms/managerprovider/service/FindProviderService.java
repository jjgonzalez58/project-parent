package com.stc.wms.managerprovider.service;

import com.stc.wms.managerprovider.controller.ProviderController;
import com.stc.wms.managerprovider.dto.ProviderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("findProviders")
@Scope(value = "singleton",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FindProviderService {
    private ProviderController providerController;


    @Autowired
    public FindProviderService(ProviderController providerController) {
        this.providerController = providerController;
    }

    public List<ProviderDTO> providerList() {
        return providerController.providers();
    }
}
