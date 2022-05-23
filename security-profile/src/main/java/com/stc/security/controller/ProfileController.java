package com.stc.security.controller;

import com.stc.security.dto.ProfileDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @PostMapping("/security/profile/save")
    ResponseEntity<ProfileDTO> saveProfile(@RequestBody ProfileDTO profolie){
        return ResponseEntity.ok(new ProfileDTO());
    };

    @GetMapping("/security/profile/all")
    ResponseEntity<List<ProfileDTO>> loadAll(){
        return ResponseEntity.ok(new ArrayList<>());
    };
}
