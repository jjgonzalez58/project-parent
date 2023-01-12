package com.stc.wms.security.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * User: job
 * Date: 1/12/22
 * Time: 18:05
 *
 * @author job
 */
@Getter
@Setter
public class SessionModel {
    public static final String BEAN_NAME = "sessionModel";

    private String userName;
    private String passwor;
    private String sessionId;
    private Calendar fechaInicio;
}
