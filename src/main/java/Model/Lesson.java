/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Jeroen Roovers
 */
public class Lesson {

    private long id;
    private Calendar startTime;
    private Calendar endTime;
    private String location;
    private Course course;
    //private Teacher teacher ?
    //private list cursists ?
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

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
    
    public static String format(Calendar calendar){
    SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
    fmt.setCalendar(calendar);
    String dateFormatted = fmt.format(calendar.getTime());
    return dateFormatted;
}

    public String getLessonString() {
        return format(startTime) + " - " + location;
    }

}
