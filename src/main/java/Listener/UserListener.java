/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import View.Pages.ManageAccountBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author Antonio
 */
public class UserListener implements ValueChangeListener {

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {

        //Access ManageAccountBean directly
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ManageAccountBean manageAccountBean = (ManageAccountBean) facesContext.getApplication().createValueBinding("#{manageAccountBean}").getValue(facesContext);
        manageAccountBean.setUserID(manageAccountBean.getUserID());
    }
}
