package com.stc.wms.navigation.bean;

import com.stc.wms.navigation.model.NavigationModel;
import com.stc.wms.navigation.model.PanelService;
import com.stc.wms.navigation.repository.NavRepository;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;

import java.util.List;

import static com.stc.wms.navigation.bean.MainBean.NAVIGATION;


/**
 * User: job
 * Date: 27/04/22
 * Time: 18:52
 *
 * @author job
 */

public class SidebarBean {
    private NavigationModel navigationModel;
    private String userName = "Admin";
    private List<PanelService> menuList;
    private boolean collapsed = false; //sidebar is collapsed for narrow screen

    @Init
    public void init(@ScopeParam(NAVIGATION)NavigationModel navModel){
        navigationModel = navModel;
        menuList = NavRepository.queryMenu();
    }

    @Command
    public void navigate(@BindingParam("menu") PanelService menu){
        String targetPath = menu.getPath();
        if (!targetPath.equals(NavigationModel.HOME_PAGE)) {
            navigationModel.setContentUrl(targetPath);
            BindUtils.postNotifyChange(null, null, navigationModel, "contentUrl");
        }
    }

    // medium breakpoint 768 + 190 (sidebar width)
    @MatchMedia("all and (min-width: 958px)")
    @NotifyChange("collapsed")
    public void beWide(){
        collapsed = false;
    }

    @MatchMedia("all and (max-width: 957px)")
    @NotifyChange("collapsed")
    public void beNarrow(){
        collapsed = true;
    }

    public String getUserName() {
        return userName;
    }

    public List<PanelService> getMenuList() {
        return menuList;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    public NavigationModel getNavigationModel() {
        return navigationModel;
    }
}
