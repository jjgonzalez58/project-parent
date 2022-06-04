package com.stc.wms.navigation.model;

import lombok.Data;

/**
 * User: job
 * Date: 27/04/22
 * Time: 18:49
 *
 * @author job
 */
@Data
public class NavigationModel {
    public static final String HOME_PAGE = "~./home.zul";
    public static final String SIDEBAR_PAGE = "~./sidebar.zul";
    public static final String SIDEBAR_PAGE_TWO = "~./sidebar-two.zul";
    private String contentUrl = HOME_PAGE;
    private String sidebar = SIDEBAR_PAGE;
}
