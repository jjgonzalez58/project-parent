package com.stc.wms.navigation.bean;

import com.stc.wms.navigation.model.NavigationModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Desktop;

/**
 * User: job
 * Date: 27/04/22
 * Time: 18:48
 *
 * @author job
 */
@Slf4j
@Data
public class MainBean {
    public static final String NAVIGATION = "navigation";
    private NavigationModel navigationModel;

    @Init
    public void init(@ContextParam(ContextType.DESKTOP) Desktop desktop){
        navigationModel = new NavigationModel();
        desktop.setAttribute(NAVIGATION, navigationModel);
    }
}
