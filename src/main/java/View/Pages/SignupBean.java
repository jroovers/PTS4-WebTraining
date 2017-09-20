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
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jeroen, antonio
 */
@Named(value = "signupBean")
@RequestScoped
public class SignupBean {

    @Inject
    CourseService cs;
    @Inject
    LessonService ls;
    
    private Course course;
    private String name;
    private String lastname;
    private List<Course> courses;
    private List<Lesson> lessons;

    /**
     * Creates a new instance of signupBean
     */
    public SignupBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void signUp() {
        System.out.println(name + lastname);
    }

    public List<Course> getAllCourses() {
        courses = cs.getAllCourses();
        return courses;
    }
    
    public List<Lesson> getAllLessons() {
        lessons = ls.getLessons();
        return lessons;
    }

}
