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
import javax.faces.event.ValueChangeEvent;
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

    private Course selectedCourse;
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

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course course) {
        this.selectedCourse = course;
    }

    public void signUp() {
        System.out.println(name + lastname);
    }

    public void valueChanged(ValueChangeEvent e) {
        String code = "";
        code = e.getNewValue().toString();
        System.out.println(code);
    }

    public List<Course> getAllCourses() {
        courses = cs.getAllCourses();
        return courses;
    }

    public List<Lesson> getAllLessonsFromCourse() {
        lessons = ls.getLessonsFromCourse(selectedCourse.getId());
        return lessons;
    }

}
