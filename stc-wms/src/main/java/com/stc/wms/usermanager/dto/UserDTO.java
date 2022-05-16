package com.stc.wms.usermanager.dto;

import lombok.Data;

/**
 * User: job
 * Date: 9/05/22
 * Time: 22:31
 *
 * @author job
 */
@Data
public class UserDTO {
    private Integer userid;
    private String name;
    private String lastname;
    private String alias;
    private String password;
    private int rolid;
}
