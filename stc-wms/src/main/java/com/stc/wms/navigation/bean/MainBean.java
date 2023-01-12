package com.stc.wms.navigation.bean;

import com.stc.wms.navigation.model.NavigationModel;
import com.stc.wms.security.admin.SecurityWebUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

/**
 * User: job
 * Date: 27/04/22
 * Time: 18:48
 *
 * @author job
 */
@Slf4j
@Data
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
@Component("mainBean")
public class MainBean {
    public static final String NAVIGATION = "navigation";
    public static final String MAINBEAN = "mainBean";
    private NavigationModel navigationModel;
    @WireVariable("securityWebUtils")
    private SecurityWebUtils webUtils;
    private Desktop desktop;




    @Init
    public void init(@ContextParam(ContextType.DESKTOP) Desktop desktop){
        this.desktop = desktop;
        navigationModel = new NavigationModel();
        navigationModel.setSidebar("");
        desktop.setAttribute(NAVIGATION, navigationModel);
        log.info("Session "+desktop.getSession());
        boolean sesionCreate = webUtils.validateSession(desktop.getSession());
        if (!sesionCreate) {
            navigationModel.setVisibleSidebar(false);
            BindUtils.postNotifyChange(null, null, navigationModel, "sidebar");
            showWindow("~./login-form.zul", "Inicio de Session");
            return;
        }
        navigationModel.setContentUrl(NavigationModel.HOME_PAGE);
        navigationModel.setSidebar(NavigationModel.SIDEBAR_PAGE);
        navigationModel.setVisibleSidebar(true);
        BindUtils.postNotifyChange(null, null, navigationModel, "contentUrl");
        BindUtils.postNotifyChange(null, null, navigationModel, "sidebar");
    }
    public void showWindow(String panel,String title){
        Map arg = new HashMap();
        Window windowComponent = (Window) Executions.createComponents(panel,null,arg);
        windowComponent.setId("vnt"+System.currentTimeMillis());
        windowComponent.setTitle(title);
        windowComponent.setClosable(false);
        windowComponent.doHighlighted();
        windowComponent.setPosition("center");
        windowComponent.setSizable(false);
        this.desktop.getSession().setAttribute("LOGIN_PANEL",windowComponent);

    }
}
