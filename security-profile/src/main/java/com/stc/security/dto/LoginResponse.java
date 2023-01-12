package com.stc.security.dto;

import lombok.Data;

/**
 * User: job
 * Date: 4/06/22
 * Time: 22:46
 *
 * @author job
 */
@Data
public class LoginResponse {
    private int codError;
    private String messageError;
    private UserDTO loginUser;
}
