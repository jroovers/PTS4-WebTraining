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
import Model.User;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jeroen, antonio
 */
@ManagedBean(name = "signupBean")
@SessionScoped
public class SignupBean {

    @Inject
    CourseService cs;
    @Inject
    LessonService ls;

    private long courseID;
    private long lessonID;
    private Course selectedCourse;
    private String name;
    private String surname;
    private String email;
    private String phonenr;
    private String courseCode;
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
        return surname;
    }

    public void setLastname(String lastname) {
        this.surname = lastname;
    }

    public long getCourseID() {
        return courseID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenr() {
        return phonenr;
    }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
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

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course course) {
        this.selectedCourse = course;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void signUp() {
        User user = new User(name, surname, phonenr, email);
        long id = ls.signUpUser(lessonID, 1);
        FacesContext context = FacesContext.getCurrentInstance();
        if (id != 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Training opgeslagen", "!"));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Training niet opgeslagen!", "Er is iets fouts gegaan! Probeer het later opnieuw"));
        }
        if (name == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Naam moet ingevuld zijn", "!"));
        }
        if (surname == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Achternaam moet ingevuld zijn", "!"));
        }
        if (email == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email moet ingevuld zijn", "!"));
        }
        if (phonenr == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Telefoon nummer moet ingevuld zijn", "!"));
        }

    }

    public List<Course> getAllCourses() {
        courses = cs.getAllCourses();
        return courses;
    }

    public void valueChanged(ValueChangeEvent e) {
        String code;
        code = e.getNewValue().toString();
        System.out.println(code);
    }

    public List<Lesson> getAllLessonsFromCourse() {
        lessons = ls.getLessonsFromCourse(courseID);
        return lessons;
    }

    public boolean checkUserDetails() {
        boolean detailsChecked = true;
        FacesContext context = FacesContext.getCurrentInstance();
        if (name == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Voer uw naam in", "!"));
            detailsChecked = false;
        }
        if (surname == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Voer uw achternaam in", "!"));
            detailsChecked = false;
        }
        if (email == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Voer uw email in", "!"));
            detailsChecked = false;
        }
        if (phonenr == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Voer uw nummer in", "!"));
            detailsChecked = false;
        }
        return detailsChecked;
    }
}
