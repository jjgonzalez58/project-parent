package com.stc.wms.shared.bean;


/**
 * User: job
 * Date: 4/06/22
 * Time: 19:18
 *
 * @author job
 */
public enum SharedExecution {
    WINDOWENBEDED("WE");

    private final String value;
     SharedExecution(String value){
        this.value = value;
    }
    public String getValue(){
         return this.value;
    }
}
