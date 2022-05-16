package com.stc.wms.loginuser.model;

import com.stc.wms.loginuser.model.dto.PerfilDto;
import lombok.Data;

import java.util.List;

/**
 * User: job
 * Date: 7/05/22
 * Time: 21:40
 *
 * @author job
 */
@Data
public class LoginResponse {
    private int codError;
    private String messageError;
    private List<PerfilDto> perfiles;
}
