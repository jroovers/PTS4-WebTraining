/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.CourseService;
import Model.Course;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author Jeroen Roovers
 */
@Named(value = "scheduleBean")
@RequestScoped
public class ScheduleBean 
{   /**
     * Creates a new instance of scheduleBean
     */
    private long courseID;
    private Course selectedCourse;
    private List<Course> allCourses;
    @Inject
    CourseService cs;
    
    public ScheduleBean() 
    {
        
    }
    
    public long getCourseID()
    {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
        setSelectedCourse();
    }
    
    public List<Course> getAllCourses() 
    {
        allCourses = cs.getAllCourses();
        
        return allCourses;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }
    
    public void setSelectedCourse()
    {
        for(Course c : allCourses)
        {
            if(c.getId() == courseID)
            {
                selectedCourse = c;
            }
        }
    }
    
    public void signup()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        if(selectedCourse == null)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Selecteer een cursus",""));
        }
    }
    
    public void valueChanged(ValueChangeEvent e) 
    {
        String code;
        code = e.getNewValue().toString();
        System.out.println(code);
    }
    

    
}
