package com.stc.security.controller;

import com.stc.security.dto.ProfileDTO;
import com.stc.security.service.ManagerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: job
 * Date: 22/05/22
 * Time: 15:12
 *
 * @author job
 */
@RestController
public class ProfileController {

    private final ManagerProfile managerProfile;


    @Autowired
    public ProfileController(ManagerProfile profile){
        this.managerProfile = profile;
    }
    @PostMapping("/security/profile/save")
    ResponseEntity<ProfileDTO> saveProfile(@RequestBody ProfileDTO profolie){
        return ResponseEntity.ok(managerProfile.createProfile(profolie));
    };

    @GetMapping("/security/profile/all")
    ResponseEntity<List<ProfileDTO>> loadAll(){
        return ResponseEntity.ok(managerProfile.loadAllProfiles());
    };
}
