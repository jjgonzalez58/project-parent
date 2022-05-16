package com.stc.wms.managerprovider.controller;

import com.stc.wms.managerprovider.dto.ProviderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "tpv-service-provider", url="localhost:9090")
public interface ProviderController {

    @GetMapping("providers/list")
    public List<ProviderDTO> providers();

    @PostMapping("/provider/save")
    public ResponseEntity<Object> createProvider(@RequestBody ProviderDTO providerDTO);


}
