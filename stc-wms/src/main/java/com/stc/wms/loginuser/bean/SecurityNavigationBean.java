package com.stc.wms.loginuser.bean;

import com.stc.wms.loginuser.model.LoginRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;

/**
 * User: job
 * Date: 7/05/22
 * Time: 08:34
 *
 * @author job
 */
@Slf4j
@Data
public class SecurityNavigationBean {
    private LoginRequest loginRequest;

//    @Autowired
    public SecurityNavigationBean( ){
//        this.service = service;
        loginRequest = new LoginRequest();
    }

    @Command
    public void  login(){
        log.info("Clic en login******** "+loginRequest.toString());
//        this.service.login(loginRequest);
    }

}