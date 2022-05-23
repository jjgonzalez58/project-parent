package com.stc.wms.managerprovider.service;

import com.stc.wms.managerprovider.controller.ProviderController;
import com.stc.wms.managerprovider.dto.ProviderDTO;
import com.stc.wms.usermanager.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Service("findProvider")
public class FindProviderService {
    private final ProviderController providerController;


    @Autowired
    public FindProviderService(ProviderController providerController) {
        this.providerController = providerController;
    }

    public List<ProviderDTO> providerList(){
        return providerController.providers();
    }
}
