package com.stc.wms.usermanager.bean;

import com.stc.wms.usermanager.dto.UserDTO;
import com.stc.wms.usermanager.service.CreateUserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

/**
 * User: job
 * Date: 9/05/22
 * Time: 22:30
 *
 * @author job
 */
@Data
@Slf4j
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CreateUserBean {

    private UserDTO user;
    private String errorMessage;

    @WireVariable("createUserService")
    private CreateUserService service;

    @Init
    public void init(){
        aditionalClear();
    }


    @Command
    @NotifyChange({"user","errorMessage"})
    public void saved(){
        setErrorMessage(null);
        if (user.getName() == null || this.user.getName().isEmpty()){
            log.error("nombre no es valido");
            setErrorMessage("Nombre No valido");
            return;
        }

        if (this.user.getAlias() == null || this.user.getAlias().isEmpty()){
            log.error("El alias no puede ser  vacio");
            setErrorMessage("Alias No valido");
            return;
        }
        if (this.user.getPassword() == null || this.user.getPassword().isEmpty()){
            log.info("El password  no puede estar vacio");
            setErrorMessage("password No valido");
            return;
        }

        this.service.saveUser(this.user);

        this.user = new UserDTO();
    }

    private void aditionalClear(){
        this.user = new UserDTO();
        this.errorMessage = null;
    }
}
