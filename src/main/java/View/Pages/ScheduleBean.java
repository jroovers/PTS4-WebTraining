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
import javax.faces.context.ExternalContext;
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
    
    public ScheduleBean(long l)
    {
        this.courseID = l;
    }
    
    public long getCourseID()
    {
        return courseID;
    }

    public void setCourseID(long courseID) 
    {
        this.courseID = courseID;
    }
    
    public List<Course> getAllCourses() 
    {
        allCourses = cs.getAllCourses();
        
        return allCourses;
    }

    public Course getSelectedCourse() 
    {
        return selectedCourse;
    }
    
    public void setSelectedCourse()
    {
        for(Course course : allCourses)
        {
            if(course.getId() == courseID)
            {
                selectedCourse = course;
            }
        }
    }
    //Werkt nog niet (button)
    public void signup()
    {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String s2 = ec.getRequestParameterMap().get("hiddenForm:hiddenLessonID");
        FacesContext context = FacesContext.getCurrentInstance();
        if(s2 == null)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Selecteer een cursus",""));
        }
    }
    
    public void valueChanged(ValueChangeEvent e) 
    {
        courseID = (long)e.getNewValue();
        setSelectedCourse();
    }
    

    
}
