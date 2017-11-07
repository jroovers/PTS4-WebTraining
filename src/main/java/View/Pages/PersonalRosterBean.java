package View.Pages;

import Controller.CourseService;
import Controller.LessonService;
import InfoSupportWeb.utility.AuthorizationUtils;
import Model.Lesson;
import Model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
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

    private CourseService courseService;
    private LessonService lessonService;
    private Lesson selectedLesson;

    @PostConstruct
    public void init()
    {
        courseService = new CourseService();
        lessonService = new LessonService();
        lessonSchedule = new DefaultScheduleModel();
        RefreshLessons();
    }

    private void RefreshLessons()
    {
        HttpSession hs = AuthorizationUtils.getSession();

        int id = (int) hs.getAttribute("UserID");
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

    public void addEvent(ActionEvent actionEvent)
    {
        if (event.getId() == null)
        {
            lessonSchedule.addEvent(event);
        } else
        {
            lessonSchedule.updateEvent(event);
        }
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent)
    {
        event = (ScheduleEvent) selectEvent.getObject();
        setSelectedLesson((Lesson) event.getData());
    }

    public void onDateSelect(SelectEvent selectEvent)
    {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event)
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event)
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    private void addMessage(FacesMessage message)
    {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
