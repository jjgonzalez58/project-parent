package com.stc.wms.loginuser.model;

import lombok.Data;

/**
 * User: job
 * Date: 7/05/22
 * Time: 08:35
 *
 * @author job
 */
@Data
public class LoginRequest {
    private String loginUser;
    private String loginPass;
    private String token;
}
