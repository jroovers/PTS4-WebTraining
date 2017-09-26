/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import View.Pages.SignupBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author Mijic
 */
public class CourseValueListener implements ValueChangeListener {

    @Override
    public void processValueChange(ValueChangeEvent event)
            throws AbortProcessingException {

        //access country bean directly
        SignupBean course = (SignupBean) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("course");

        course.setCourseID(Long.valueOf(event.getNewValue().toString()));
    }

}
