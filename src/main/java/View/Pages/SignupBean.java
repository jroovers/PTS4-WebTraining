package View.Pages;

import Controller.CourseService;
import Controller.EnrollmentService;
import Controller.LessonService;
import Controller.UserService;
import Model.Course;
import Model.Enrollment;
import Model.Lesson;
import Model.User;
import View.Session.SessionBean;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

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
    @Inject
    UserService us;
    @Inject
    EnrollmentService es;
    @Inject
    private SessionBean session;

    private long courseID;
    private long lessonID;
    private Course selectedCourse;
    private Lesson selectedLesson;
    private String name;
    private String surname;
    private String email;
    private String phonenr;
    private String courseCode;
    private List<Course> courses;
    private List<Course> filteredCourses;
    private List<Lesson> lessons;
    private List<Lesson> filteredLessons;

    private List<Enrollment> enrollments;

    /**
     * Creates a new instance of signupBean
     */
    @PostConstruct
    public void SignupBean() {
        courses = cs.getAllCourses();
        if (session.getUser() != null) {
            enrollments = es.getEnrollmentsByUser((int) session.getUser().getId());
        }
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
        setLessons(ls.getLessonsFromCourse(selectedCourse.getId()));
    }

    public Lesson getSelectedLesson() {
        return selectedLesson;
    }

    public void setSelectedLesson(Lesson selectedLesson) {
        this.selectedLesson = selectedLesson;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getFilteredCourses() {
        return filteredCourses;
    }

    public void setFilteredCourses(List<Course> filteredCourses) {
        this.filteredCourses = filteredCourses;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Lesson> getFilteredLessons() {
        return filteredLessons;
    }

    public void setFilteredLessons(List<Lesson> filteredLessons) {
        this.filteredLessons = filteredLessons;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void signUp() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (session.isLoggedIn()) {
                ls.signUpUser(selectedLesson.getId(), session.getUser().getId());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Training opgeslagen", "!"));
            } else {
                if (!name.isEmpty() && !surname.isEmpty() && !phonenr.isEmpty() && !email.isEmpty()) {
                    User user = new User(name, surname, phonenr, email);
                    user.addAccessLevel(1);
                    us.addUser(user);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Training en data opgeslagen", "!"));
                }
            }
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Training niet opgeslagen!", "Er is iets fouts gegaan! Probeer het later opnieuw"));
        }
    }

    public void valueChanged(ValueChangeEvent e) {
        String code;
        code = e.getNewValue().toString();
        Logger.getLogger(SignupBean.class.getName()).log(Level.INFO, code);
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
