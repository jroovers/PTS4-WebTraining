/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import View.Pages.ScheduleBean;
import View.Pages.SaveIDBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author Jowelle
 */
public class ScheduleValueListener implements ValueChangeListener {

    @Override
    public void processValueChange(ValueChangeEvent event)
            throws AbortProcessingException {

        //access country bean directly
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ScheduleBean scheduleBean = (ScheduleBean) facesContext.getApplication().createValueBinding("#{scheduleBean}").getValue(facesContext);
        SaveIDBean saveIDBean= (SaveIDBean) facesContext.getApplication().createValueBinding("#{saveIDBean}").getValue(facesContext);
        
        scheduleBean.setLessonID(Long.valueOf(scheduleBean.getLessonID()));
        saveIDBean.setLessonID(Long.valueOf(scheduleBean.getLessonID()));
    }
    
}
