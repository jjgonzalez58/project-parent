package com.stc.wms.navigation.model;

import lombok.Data;

/**
 * User: job
 * Date: 27/04/22
 * Time: 18:54
 *
 * @author job
 */
@Data
public class PanelService {
    private String service;
    private String panel;
    private boolean enable;
    private String path = NavigationModel.HOME_PAGE;

    public PanelService(String label) {
        this.service = label;
    }
    public PanelService(String service, String panel) {
        this.enable = true;
        this.service = service;
        this.panel = panel;
    }
}
