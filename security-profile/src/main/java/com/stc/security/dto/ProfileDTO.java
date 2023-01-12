package com.stc.security.dto;

import lombok.Data;

import java.util.List;

/**
 * User: job
 * Date: 21/05/22
 * Time: 23:15
 *
 * @author job
 */
@Data
public class ProfileDTO {
    private int errorCode;
    private String errorMessage;
    private int perfilId;
    private String name;
    private String description;
    private List<ServiceDTO> serviceList;
}
