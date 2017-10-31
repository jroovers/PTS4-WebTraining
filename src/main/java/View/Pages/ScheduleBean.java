/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.LessonService;
import Model.Lesson;
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
    private long lessonID;
    private Lesson selectedLesson;
    private List<Lesson> allLessons;
    @Inject
    LessonService ls;
    
    public ScheduleBean() 
    {
        
    }
    
    public ScheduleBean(long l)
    {
        this.lessonID = l;
    }
    
    public long getLessonID()
    {
        return lessonID;
    }

    public void setLessonID(long lessonID) 
    {
        this.lessonID = lessonID;
    }
    
    public List<Lesson> getAllLessons() 
    {
        allLessons = ls.getLessons();
        
        return allLessons;
    }

    public Lesson getSelectedLesson() 
    {
        return selectedLesson;
    }
    
    public void setSelectedCourse()
    {
        for(Lesson lesson : allLessons)
        {
            if(lesson.getId() == lessonID)
            {
                selectedLesson = lesson;
            }
        }
    }
    
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
        lessonID = (long)e.getNewValue();
        setSelectedCourse();
    }
    

    
}
