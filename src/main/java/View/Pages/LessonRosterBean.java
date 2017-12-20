package View.Pages;

import Controller.LessonService;
import Model.Lesson;
import View.Session.SessionBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@Named(value = "lessonRosterBean")
@ManagedBean
@ViewScoped
public class LessonRosterBean implements Serializable {

    private transient ScheduleModel lessonSchedule;

    private transient ScheduleEvent event = new DefaultScheduleEvent();

    private transient LessonService lessonService;
    private transient Lesson selectedLesson;
    private String email;

    @PostConstruct
    public void init() {
        lessonService = new LessonService();
        lessonSchedule = new DefaultScheduleModel();

        RefreshLessons();
    }

    public void onButtonClick() {
        if (email.isEmpty()) {
            return;
        }
        RefreshLessons();
    }

    private void RefreshLessons() {
        lessonSchedule.clear();
        List<Lesson> lessons = lessonService.GetLessonsAndRegistrationsByEmail(email);
        if (lessons.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Deze gebruiker heeft geen lessen, of het Email Adres is verkeerd ingevuld", ""));
            return;
        }

        for (Lesson lesson : lessons) {
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

    public void addEvent() {
        if (event.getId() == null) {
            lessonSchedule.addEvent(event);
        } else {
            lessonSchedule.updateEvent(event);
        }
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        setSelectedLesson((Lesson) event.getData());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
