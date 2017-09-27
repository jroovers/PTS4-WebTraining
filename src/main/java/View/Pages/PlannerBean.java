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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
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
    LessonService lService;
    @Inject
    CourseService cService;

    private long courseID;
    private List<Course> courses;
    private List<Lesson> lessons;
    private Date startDate;
    private String location;

    /**
     * Creates a new instance of plannerBean
     */
    public PlannerBean() {
    }

    @PostConstruct
    public void init() {
        if (courses == null) {
            courses = cService.getAllCourses();
        }
        if (lessons == null) {
            lessons = lService.getLessons();
        }
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public String submitLesson() {
        Course selectedCourse = new Course();
        selectedCourse.setId(courseID);

        GregorianCalendar starttime = new GregorianCalendar();
        starttime.setTime(startDate);
        GregorianCalendar endtime = (GregorianCalendar) starttime.clone();
        for (Course c : courses) {
            if (courseID == c.getId()) {
                if (c.getDurationInDays() > 1) {
                    endtime.add(GregorianCalendar.DAY_OF_YEAR, c.getDurationInDays() - 1);
                }
            }
        }

        Lesson newLesson = new Lesson(selectedCourse);
        newLesson.setLocation(location);
        newLesson.setStartTime(starttime);
        newLesson.setEndTime(endtime);

        lService.addLesson(newLesson);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Training opgeslagen", "whoopizz"));
        return "planner";
    }
}
