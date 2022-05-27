package com.stc.security.service;

import com.stc.security.dto.ProfileDTO;
import com.stc.security.model.Perfil;
import com.stc.security.repository.PerfilRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: job
 * Date: 26/05/22
 * Time: 22:13
 *
 * @author job
 */
@Service
@Slf4j
public class ManagerProfile {

    private final PerfilRepository prfRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ManagerProfile(PerfilRepository repository){
        this.prfRepo = repository;
        this.modelMapper = new ModelMapper();
    }

    public ProfileDTO createProfile(ProfileDTO dto){
        Perfil  profile =  parseProfileFromDto(dto);
        if (profile.validateData()){
            Perfil prfSave = this.prfRepo.save(profile);
            dto.setPerfilId(prfSave.getPerfilId());
            dto.setErrorCode(0);
            dto.setErrorMessage("Registro guardado correctamente");
        }else {
            dto.setErrorCode(1);
            dto.setDescription("Error guardando registro");
        }
        return dto;
    }
    public List<ProfileDTO> loadAllProfiles(){
        List<Perfil> lsProfile = this.prfRepo.findAll();
        List<ProfileDTO> lsdto = new ArrayList<>();
        lsProfile.forEach(profile ->{
            lsdto.add(parseDtoFromProfile(profile));
        });
        return lsdto;
    }

    public Perfil parseProfileFromDto(ProfileDTO dto){
        return modelMapper.map(dto,Perfil.class);
    }
    public ProfileDTO parseDtoFromProfile(Perfil profile){
        return modelMapper.map(profile,ProfileDTO.class);
    }
}
