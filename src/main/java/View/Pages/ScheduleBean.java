/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.CourseService;
import Controller.LessonService;
import Model.Course;
import Model.Lesson;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private long lessonID;
    private Course selectedCourse;
    private List<Course> allCourses;
    private List<Lesson> lessonsFromCourse;
    private Set<String> locations;
    
    @Inject
    CourseService cs;
    @Inject
    LessonService ls;
    
    public ScheduleBean() 
    {
        locations = new HashSet<String>();
    }
    
    public ScheduleBean(long l)
    {
        this.courseID = l;
    }

    public Set<String> getLocations() {
        return locations;
    }

    public void setLessonID(long lessonID) {
        this.lessonID = lessonID;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public long getLessonID() {
        return lessonID;
    }

    public List<Lesson> getLessonsFromCourse() {
        return lessonsFromCourse;
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
        
        lessonsFromCourse = ls.getLessonsFromCourse(courseID);
        
        for(Lesson l : lessonsFromCourse)
        {
            locations.add(l.getLocation());
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
