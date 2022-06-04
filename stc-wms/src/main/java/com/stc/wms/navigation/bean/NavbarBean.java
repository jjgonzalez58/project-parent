package com.stc.wms.navigation.bean;

import com.stc.wms.navigation.model.NavigationModel;
import com.stc.wms.navigation.model.PanelService;
import com.stc.wms.navigation.repository.NavRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import java.util.List;

import static com.stc.wms.navigation.bean.MainBean.NAVIGATION;


/**
 * User: job
 * Date: 10/05/22
 * Time: 22:02
 *
 * @author job
 */
@Data
@Slf4j
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class NavbarBean {
    private PanelService currentPage;
    private String currentService;
    private NavigationModel navigationModel;
    private List<PanelService> panelList;

    @Init
    public void init(@ScopeParam(NAVIGATION)NavigationModel navModel){
        navigationModel = navModel;
        initialComponent();
    }
    private void initialComponent(){
        panelList = NavRepository.queryMenu();
        currentPage = homePanel();
    }

    @Command
    @NotifyChange("currentPage")
    public void chanceInternalPage(@BindingParam("service") String service){
        String targetPath = findMenu(service);
        if (targetPath.equals(NavigationModel.HOME_PAGE)) {
            log.info("Servicio: "+service);
            log.info("servicio no encontra redireccionado al home");
        }
        navigationModel.setContentUrl(targetPath);
        BindUtils.postNotifyChange(null, null, navigationModel, "contentUrl");
        currentPage.setEnable(false);
        chanceSidebar("sidebarTwo","");
    }
    @Command
    @NotifyChange("currentPage")
    public void selectedPanel(@BindingParam("panel") String panel){
        currentPage = findPanel(panel);
        currentPage.setEnable(true);
    }
    @Command
    public void chanceSidebar(@BindingParam("sidebar")String sidebar, @BindingParam("option")String option){
        log.info("select "+sidebar+" "+option);
        navigationModel.setSidebar(getSidebarPanel(sidebar));
        notifyComponentSidebar();
    }

    private String findMenu(String service){
        for (PanelService menu: panelList){
            if (menu.getService().equals(service))
                return menu.getPanel();
        }
        return NavigationModel.HOME_PAGE;
    }
    private PanelService findPanel(String service){
        for (PanelService panel: panelList){
            if (panel.getService().equals(service))
                return panel;
        }
        return homePanel();
    }

    private String getSidebarPanel(String sidebar){
        if ("sidebar".equals(sidebar))
            return NavigationModel.SIDEBAR_PAGE;
        else return NavigationModel.SIDEBAR_PAGE_TWO;
    }
    private void notifyComponentSidebar(){
        BindUtils.postNotifyChange(null, null, navigationModel, "sidebar");
    }
    private PanelService homePanel(){
        return new PanelService("home", "~./home.zul");
    }
}
