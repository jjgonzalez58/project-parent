package com.stc.wms.loginuser.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * User: job
 * Date: 5/01/23
 * Time: 14:20
 *
 * @author job
 */
@Getter
@Setter
public class ValidateUserRequest {
    private String user;
    private String pass;
}
