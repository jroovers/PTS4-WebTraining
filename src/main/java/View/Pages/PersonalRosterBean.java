package View.Pages;

import Controller.CourseService;
import Controller.LessonService;
import InfoSupportWeb.utility.CourseDAOUtils;
import Model.Course;
import Model.Lesson;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

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

    private ScheduleModel eventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    private CourseService courseService;
    private LessonService lessonService;
    private Lesson selectedLesson;

    // <editor-fold desc="Default event stuff" >
    @PostConstruct
    public void init()
    {
        courseService = new CourseService();
        lessonService = new LessonService();

        eventModel = new DefaultScheduleModel();
        for (Lesson l : lessonService.getLessonsFromCourse(2))
        {
            eventModel.addEvent(new DefaultScheduleEvent(l.getCourse().getName(), l.getStartTime().getTime(), l.getEndTime().getTime(), l));

        }
    }

    public ScheduleModel getEventModel()
    {
        return eventModel;
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
            eventModel.addEvent(event);
        } else
        {
            eventModel.updateEvent(event);
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