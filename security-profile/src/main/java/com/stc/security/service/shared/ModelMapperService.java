package com.stc.security.service.shared;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


/**
 * User: job
 * Date: 28/05/22
 * Time: 07:37
 *
 * @author job
 */
@Service
public class ModelMapperService {

    private final ModelMapper  modelMapper;
    public ModelMapperService(){
        this.modelMapper = new ModelMapper();
    }
    public Object parserServiceFromDto(Object objectDto, Object clazz){
        return modelMapper.map(objectDto,clazz.getClass());
    }
    public Object parseDtoFromService(Object objectClazz,Object clazzDto){
        return modelMapper.map(objectClazz,clazzDto.getClass());
    }
}
