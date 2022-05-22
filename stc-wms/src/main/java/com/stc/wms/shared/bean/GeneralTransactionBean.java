package com.stc.wms.shared.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;


/**
 * User: job
 * Date: 21/05/22
 * Time: 11:56
 *
 * @author job
 */
@Data
@Slf4j
public class GeneralTransactionBean implements GeneralTransaction {
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
}
