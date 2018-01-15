package View.Pages;

import Controller.LessonService;
import Model.Lesson;
import View.Session.SessionBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;


/**
 * 
 * @author Ricardo, Jeroen
 */
@Named(value = "personalRosterBean")
@SessionScoped
public class PersonalRosterBean implements Serializable {

    @Inject
    private SessionBean session;

    private transient ScheduleModel lessonSchedule;
    private transient ScheduleEvent event;
    private transient LessonService lessonService;
    private transient Lesson selectedLesson;

    @PostConstruct
    public void init() {
        lessonService = new LessonService();
        lessonSchedule = new DefaultScheduleModel();
        RefreshLessons();
    }

    /**
     * Get the lessons from the database and add them to the calendar
     */
    private void RefreshLessons() {
        long id = session.getUser().getId();
        lessonSchedule.clear();
        for (Lesson lesson : lessonService.GetLessonsAndRegistrationsByTeacher(id)) {
            lessonSchedule.addEvent(new DefaultScheduleEvent(lesson.getCourse().getName(), lesson.getStartTime().getTime(), lesson.getEndTime().getTime(), lesson));
        }
    }

    public ScheduleModel getEventModel() {
        return lessonSchedule;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public Lesson getSelectedLesson() {
        return selectedLesson;
    }

    public void setSelectedLesson(Lesson selectedLesson) {
        this.selectedLesson = selectedLesson;
    }

  public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        selectedLesson = (Lesson)event.getData();
    }
}
