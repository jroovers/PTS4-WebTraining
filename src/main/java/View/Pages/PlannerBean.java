/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.CourseService;
import Controller.LessonService;
import Controller.LocationService;
import Model.Course;
import Model.Lesson;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
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
    LessonService lessonService;
    @Inject
    CourseService courseService;
    @Inject
    LocationService locationService;

    private long courseID;
    private long lessonID;
    private String location;
    private List<Course> courses;
    private List<Lesson> lessons;
    private List<String> locations;
    private Date startDate;

    /**
     * Creates a new instance of plannerBean
     */
    public PlannerBean() {
    }

    @PostConstruct
    public void init() {
        if (courses == null) {
            courses = courseService.getAllCourses();
        }
        if (lessons == null) {
            lessons = lessonService.getLessons();
            Comparator<Lesson> timecompare = (Lesson o1, Lesson o2) -> o1.getStartTime().compareTo(o2.getStartTime());
            Collections.sort(lessons, timecompare);
        }
        if (locations == null) {
            locations = locationService.getLocations();
            Collections.sort(locations);
        }
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public long getLessonID() {
        return lessonID;
    }

    public void setLessonID(long lessonID) {
        this.lessonID = lessonID;
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

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String submitLesson() {
        Course selectedCourse = new Course();
        selectedCourse.setId(courseID);

        GregorianCalendar starttime = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        starttime.setTime(startDate);
        starttime.add(Calendar.HOUR_OF_DAY, 12);
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

        lessonService.addLesson(newLesson);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Training opgeslagen", "whoopizz"));
        return "planner?faces-redirect=true";
    }

    public String deleteLesson() {
        lessonService.deleteLesson(lessonID);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Les verwijderd", "whoopizz"));
        return "planner?faces-redirect=true";

    }
}
