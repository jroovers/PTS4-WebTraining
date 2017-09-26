/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.LessonService;
import Model.Course;
import Model.Lesson;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Jeroen Roovers
 */
@Named(value = "plannerBean")
@RequestScoped
public class PlannerBean {

    @Inject
    LessonService service;

    private long courseID;
    private Date startDate;
    private String location;
    private String feedbackMessage;

    /**
     * Creates a new instance of plannerBean
     */
    public PlannerBean() {
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }

    public String submitLesson() {
        GregorianCalendar time = new GregorianCalendar();
        time.setTime(startDate);

        Course selectedCourse = new Course();
        selectedCourse.setId(courseID);

        Lesson newLesson = new Lesson(selectedCourse);
        newLesson.setLocation(location);
        newLesson.setStartTime(time);
        
        //service.addLesson(newLesson);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Training opgeslagen"));
        return "planner";
    }
}
