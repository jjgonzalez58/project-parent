package com.stc.wms.navigation.model;

/**
 * User: job
 * Date: 27/04/22
 * Time: 18:49
 *
 * @author job
 */
public class NavigationModel {
    public static final String HOME_PAGE = "~./home.zul";
    private String contentUrl = HOME_PAGE;
    public String getContentUrl() {
        return contentUrl;
    }
    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
