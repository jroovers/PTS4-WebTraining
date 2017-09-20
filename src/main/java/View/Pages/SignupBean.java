/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.CourseService;
import Model.Course;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author jeroe
 */
@Named(value = "signupBean")
@RequestScoped
public class SignupBean {

    private Course course;
    private String name;
    private String lastname;
    CourseService cs;
    List<Course> courses;

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

    public void getAllCourses() {
        courses = cs.getAllCourses();
    }

}
