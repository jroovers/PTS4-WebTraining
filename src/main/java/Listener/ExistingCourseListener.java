/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import View.Pages.CoursesBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author Jeroen Roovers
 */
public class ExistingCourseListener implements ValueChangeListener {

    @Override
    public void processValueChange(ValueChangeEvent event)
            throws AbortProcessingException {

        // Access bean directly
        FacesContext facesContext = FacesContext.getCurrentInstance();
        CoursesBean coursesBean = (CoursesBean) facesContext.getApplication().createValueBinding("#{coursesBean}").getValue(facesContext);
        
        coursesBean.setSelectedCode(coursesBean.getSelectedCode());
    }

}
