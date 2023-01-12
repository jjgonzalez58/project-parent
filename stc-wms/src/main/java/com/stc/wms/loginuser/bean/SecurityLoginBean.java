package com.stc.wms.loginuser.bean;

import com.stc.wms.loginuser.model.LoginRequest;
import com.stc.wms.loginuser.model.LoginResponse;
import com.stc.wms.loginuser.model.dto.ValidateUserResponse;
import com.stc.wms.navigation.model.NavigationModel;
import com.stc.wms.security.admin.SecurityWebUtils;
import com.stc.wms.security.dto.UserDTO;
import com.stc.wms.shared.bean.GeneralTransactionBean;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import java.io.IOException;

import static com.stc.wms.navigation.bean.MainBean.NAVIGATION;

/**
 * User: job
 * Date: 7/05/22
 * Time: 08:34
 *
 * @author job
 */
@Slf4j
@Getter
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SecurityLoginBean extends GeneralTransactionBean {
    private LoginRequest loginRequest;
    private NavigationModel navigationModel;
    private Desktop desktop;

    @WireVariable("securityWebUtils")
    private SecurityWebUtils webUtils;

    @Init
    public void init(@ContextParam(ContextType.DESKTOP) Desktop desktop, @ScopeParam(NAVIGATION)NavigationModel navModel){
        navigationModel = navModel;
        this.desktop = desktop;
        this.loginRequest = new LoginRequest();
    }

    @Command
    @NotifyChange({"loginRequest"})
    public void  login() throws IOException {
        if (isEmpty(this.loginRequest.getLoginUser())){
            showErrorMessageBox("Debe ingresar un usuario para registrarse");
            return;
        }
        if (isEmpty(this.loginRequest.getLoginPass())){
            showErrorMessageBox("Debe ingresar una clave de acceso");
            return;
        }
        ValidateUserResponse userResponse;
        try {
            userResponse = webUtils.validateUser(this.loginRequest);
        }catch (Exception e){
            log.error("Error en conexion al cliente.. ");
            showErrorMessageBox("No se pudo obtener informacion del usuario");
            return;
        }
        if (userResponse == null || userResponse.getErrorCode() != 0){
            log.info("Usuario no valido*****");
            showErrorMessageBox("Error  de credenciales "+ userResponse.getMessage());
            return;
        }
        this.loginRequest.setToken(webUtils.tokenSession(this.loginRequest.getLoginUser()));
        LoginResponse loginResponse = webUtils.getLoginUser(this.loginRequest);
        if (loginResponse.getCodError() == 0){
            UserDTO userDTO = loginResponse.getLoginUser();
            log.info("datos de usuario obtenido "+userDTO.getName());
            webUtils.createUserSession(this.desktop.getSession(),this.loginRequest.getLoginUser(), this.loginRequest.getToken());
            Window winLogin = (Window) this.getDesktop().getSession().getAttribute("LOGIN_PANEL");
            if (winLogin != null) {
                BindUtils.postNotifyChange(null, null, navigationModel, "visibleSidebar");
                winLogin.setVisible(false);
                Executions.sendRedirect("http://localhost:8082");
            }
        }else {
            this.loginRequest = new LoginRequest();
            super.showErrorMessageBox(loginResponse.getMessageError());
        }

    }

}