package com.stc.security.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * User: job
 * Date: 5/01/23
 * Time: 14:15
 *
 * @author job
 */
@Setter
@Getter
public class ValidateUserResponse {
    private int errorCode;
    private String message;
}
