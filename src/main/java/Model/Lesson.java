package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author Jeroen, Ricardo
 */
public class Lesson {

    private long id;
    private Calendar startTime;
    private Calendar endTime;
    private String location;
    private Course course;
    private User teacher;

    private Collection<User> registrations;

    public Lesson(Course course) {
        this.course = course;
    }

    public Lesson(long id, Calendar startTime, Calendar endTime, String location, Course course) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.course = course;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Collection<User> getRegistrations() {
        return registrations;
    }
    
    public void setRegistrations(Collection<User> registrations) {
        this.registrations = registrations;
    }
    
    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public static String format(Calendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }

    public String getLessonString() {
        return format(startTime) + " - " + location + " - " + course.getName();
    }
}
