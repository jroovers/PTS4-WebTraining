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
 * @author Jeroen Roovers, Ricardo van Dijke
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

    private String location;
    private List<Course> courses;
    private List<Lesson> lessons;
    private Lesson selectedLesson;
    private Course selectedCourse;

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
            //filteredLessons = lessons;
            Comparator<Lesson> timecompare = (Lesson o1, Lesson o2) -> o1.getStartTime().compareTo(o2.getStartTime());
            Collections.sort(lessons, timecompare);
        }
        if (locations == null) {
            locations = locationService.getLocations();
            Collections.sort(locations);
        }
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

    public Lesson getSelectedLesson() {
        return selectedLesson;
    }

    public void setSelectedLesson(Lesson selectedLesson) {
        this.selectedLesson = selectedLesson;
    }
        public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course course) {
        this.selectedCourse = course;
        System.out.println(selectedCourse.getShortString());
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String submitLesson() {
        System.out.println(selectedCourse.getShortString());
        GregorianCalendar starttime = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        starttime.setTime(startDate);
        starttime.add(Calendar.HOUR_OF_DAY, 12);
        GregorianCalendar endtime = (GregorianCalendar) starttime.clone();

        if (selectedCourse.getDurationInDays() > 1) {
            endtime.add(GregorianCalendar.DAY_OF_YEAR, selectedCourse.getDurationInDays() - 1);
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
        lessonService.deleteLesson(selectedLesson.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Les verwijderd", "whoopizz"));
        return "planner?faces-redirect=true";
    }
}