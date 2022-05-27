package com.stc.wms.navigation.repository;


import com.stc.wms.navigation.model.PanelService;

import java.util.LinkedList;
import java.util.List;

/**
 * User: job
 * Date: 27/04/22
 * Time: 18:58
 *
 * @author job
 */
public class NavRepository {
    private static List<PanelService> panelList = new LinkedList<>();

    static{
        initMenus();
    }

    static public void initMenus(){
        PanelService menuT = new PanelService("admin-user", "~./user/create-user.zul");
        panelList.add(menuT);
        PanelService menuHome = new PanelService("home", "~./home.zul");
        panelList.add(menuHome);
        PanelService menuTable = new PanelService("Tables", "z-icon-flag-o");
        panelList.add(menuTable);
        // kevin borge
        PanelService menuP = new PanelService("create-provider", "~./provider/create-provider.zul");
        panelList.add(menuP);
        PanelService tableProviders = new PanelService("load-table", "~./provider/table-providers.zul" );
        panelList.add(tableProviders);

        PanelService menuC = new PanelService("Contact", "z-icon-envelope-o");
//        menuC.setCounter(Util.nextInt(1, 10));
        panelList.add(menuC);
        PanelService menuF = new PanelService("Freeze", "z-icon-snowflake-o");
        panelList.add(menuF);
        PanelService menuL = new PanelService("Logout", "z-icon-power-off");
        panelList.add(menuL);
        PanelService menuIcon = new PanelService("icons", "~./icons.zul");
        panelList.add(menuIcon);

    }


    static public List<PanelService> queryMenu(){
        return panelList;
    }
}
