package com.stc.wms.security.dto;

import lombok.Data;

import java.util.List;

/**
 * User: job
 * Date: 4/06/22
 * Time: 14:10
 *
 * @author job
 */
@Data
public class UserDTO {
    private int errorCode;
    private String errorMessage;
    private Integer userid;
    private String name;
    private String lastName;
    private String alias;
    private String password;
    private int rol_idrol;
    private List<ProfileDTO> perfilList;
}
