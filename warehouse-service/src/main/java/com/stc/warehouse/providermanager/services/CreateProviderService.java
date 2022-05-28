package com.stc.warehouse.providermanager.services;

import com.stc.warehouse.dtos.ProviderDTO;
import com.stc.warehouse.entities.Provider;
import com.stc.warehouse.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProviderService {
    private final ProviderRepository providerRepository;
    private ProviderDTO providerDTO;


    @Autowired
    public CreateProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    private Provider parseProviderDTO(){
        Provider provider = new Provider();
        provider.setIdproveedor(this.providerDTO.getIdproveedor());
        provider.setNombre(this.providerDTO.getNombre());
        provider.setNit(this.providerDTO.getNit());
        provider.setDireccion(this.providerDTO.getDireccion());
        provider.setTelefono(this.providerDTO.getTelefono());
        provider.setContacto(providerDTO.getContacto());
        provider.setEmail(providerDTO.getEmail());
        provider.setStatus(providerDTO.getStatus());
        return provider;
    }
    public boolean createProveedor(ProviderDTO providerDTO){
        this.providerDTO = providerDTO;
        boolean wasSaved = false;
        Provider savedProveedor = parseProviderDTO();
        if (savedProveedor.validProvider()){
            this.providerRepository.save(savedProveedor);
            wasSaved = true;
        }
        return wasSaved;
    }
}
