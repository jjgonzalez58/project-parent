package com.stc.wms.security.controller;

import com.stc.wms.security.dto.ProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * User: job
 * Date: 21/05/22
 * Time: 23:19
 *
 * @author job
 */
@FeignClient(name = "securityProfile", url="localhost:8083")
public interface ProfileController {

    @PostMapping("/security/profile/save")
    ResponseEntity<ProfileDTO> saveProfile(@RequestBody ProfileDTO profolie);

    @GetMapping("/security/profile/all")
    ResponseEntity<List<ProfileDTO>> loadAllProfile();


}
