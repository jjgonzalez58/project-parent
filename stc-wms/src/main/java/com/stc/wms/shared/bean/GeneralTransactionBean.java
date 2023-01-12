package com.stc.wms.shared.bean;

import com.stc.wms.security.admin.SecurityWebUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;


/**
 * User: job
 * Date: 21/05/22
 * Time: 11:56
 *
 * @author job
 */
@Data
@Slf4j
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class GeneralTransactionBean implements GeneralTransaction {

    @WireVariable("securityWebUtils")
    private SecurityWebUtils webUtils;

    public  GeneralTransactionBean(){
    }
    public void showConfirmMessageBox(String message, GeneralTransaction transaction) {
        Messagebox.show(message, "!Importante!", Messagebox.YES | Messagebox.CANCEL,
                Messagebox.QUESTION, (EventListener<Event>) event -> {
                   if(event.getName().equals(Messagebox.ON_YES)){
                       transaction.doAccepConfirm();
                   }
                });
    }
    public void showErrorMessageBox(String message){
        Messagebox.show(message, "!Importante!", Messagebox.OK,
                Messagebox.ERROR, (EventListener<Event>) event -> {
                });
    }

    public void showSuccessMessageBox(String message){
        Messagebox.show(message, "!Información!", Messagebox.OK,
                Messagebox.INFORMATION, (EventListener<Event>) event -> {
                });
    }

    @Override
    public void doAccepConfirm() {}

    public boolean isEmpty(String field){
        return field == null || field.trim().isEmpty();
    }

    public void showWindow(String panel,String title,Object obj){
        Map arg = new HashMap();
        arg.put(SharedExecution.WINDOWENBEDED.getValue(),obj);
        Window windowComponent = (Window) Executions.createComponents("~./hightlighted/window-empty.zul",null,arg);
        Div  divComponent = (Div) Executions.createComponents(panel,windowComponent,arg);
        windowComponent.setId("vnt"+System.currentTimeMillis());
        windowComponent.setTitle(title);
        windowComponent.setClosable(true);
        windowComponent.setWidth("75%");
        windowComponent.doHighlighted();
        windowComponent.setPosition("center");
    }
}
