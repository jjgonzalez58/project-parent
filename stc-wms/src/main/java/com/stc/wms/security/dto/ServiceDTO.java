package com.stc.wms.security.dto;

import lombok.Data;

/**
 * User: job
 * Date: 21/05/22
 * Time: 08:40
 *
 * @author job
 */
@Data
public class ServiceDTO {
    private int errorCode;
    private String errorMessage;
    private int id;
    private String code;
    private String description;
}
