package View.Pages;

import Controller.LessonService;
import InfoSupportWeb.utility.AuthorizationUtils;
import Model.Lesson;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@Named(value = "personalRosterBean")
@ManagedBean
@ViewScoped
public class PersonalRosterBean implements Serializable
{
    private ScheduleModel lessonSchedule;

    private ScheduleEvent event = new DefaultScheduleEvent();

    private LessonService lessonService;
    private Lesson selectedLesson;

    @PostConstruct
    public void init()
    {
        lessonService = new LessonService();
        lessonSchedule = new DefaultScheduleModel();
        RefreshLessons();
    }

    
    /**
     * Get the lessons from the database and add them to the calendar
     */
    private void RefreshLessons()
    {
        HttpSession hs = AuthorizationUtils.getSession();

        int id = (int) hs.getAttribute("UserID"); //get logged in user's ID
        lessonSchedule.clear();
        for (Lesson lesson : lessonService.GetLessonsAndRegistrationsByTeacher(id))
        {
            lessonSchedule.addEvent(new DefaultScheduleEvent(lesson.getCourse().getName(), lesson.getStartTime().getTime(), lesson.getEndTime().getTime(), lesson));
        } 
    }

    public ScheduleModel getEventModel()
    {
        return lessonSchedule;
    }

    public ScheduleEvent getEvent()
    {
        return event;
    }

    public void setEvent(ScheduleEvent event)
    {
        this.event = event;
    }

    public Lesson getSelectedLesson()
    {
        return selectedLesson;
    }

    public void setSelectedLesson(Lesson selectedLesson)
    {
        this.selectedLesson = selectedLesson;
    }
    public void onEventSelect(SelectEvent selectEvent)
    {
        event = (ScheduleEvent) selectEvent.getObject();
        setSelectedLesson((Lesson) event.getData());
    }
}