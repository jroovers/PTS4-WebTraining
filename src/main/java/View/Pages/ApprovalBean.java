package View.Pages;

import Controller.CourseService;
import Controller.EnrollmentService;
import Model.Course;
import Model.Enrollment;
import Model.User;
import View.Session.SessionBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Jeroen Roovers
 */
@Named(value = "approvalBean")
//Scoping this to session is like using a hammer to perform surgery, but it works.
@SessionScoped
public class ApprovalBean implements Serializable {

    @Inject
    private CourseService courseService;
    @Inject
    private EnrollmentService enrollmentService;
    @Inject
    private SessionBean session;

    private Enrollment selectedEnrollment;
    private List<Enrollment> allEnrollments;
    private List<Enrollment> filteredEnrollments;
    private Course selectedCourse;
    private List<Course> courses;
    private List<Course> filteredCourses;
    private List<Enrollment> courseEnrollments;

    private final static String REDIRECT_FULL = "approval?faces-redirect=true";
    private final static String REDIRECT = "approval";

    /**
     * Creates a new instance of ApprovalBean
     */
    @PostConstruct
    public void init() {
        courses = courseService.getAllCourses();
    }

    /**
     * TODO
     *
     * @return
     */
    public String onAcceptEnrollment() {
        acceptEnrollment();
        this.courseEnrollments = enrollmentService.getAllEnrollmentsByCourseID(selectedCourse.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Akkoord", ""));
        return REDIRECT_FULL;
    }

    /**
     * TODO
     *
     * @return
     */
    public String onRejectEnrollment() {
        declineEnrollment();
        this.courseEnrollments = enrollmentService.getAllEnrollmentsByCourseID(selectedCourse.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Afgewezen", ""));
        return REDIRECT_FULL;
    }

    /**
     *
     * @return
     */
    public String onDeleteEnrollment() {
        deleteEnrollment();
        this.courseEnrollments = enrollmentService.getAllEnrollmentsByCourseID(selectedCourse.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Verwijderd", ""));
        return REDIRECT_FULL;
    }

    public Enrollment getSelectedEnrollment() {
        return selectedEnrollment;
    }

    public void setSelectedEnrollment(Enrollment selectedEnrollment) {
        this.selectedEnrollment = selectedEnrollment;
    }

    public List<Enrollment> getAllEnrollments() {
        allEnrollments = enrollmentService.getAllEnrollments();
        return allEnrollments;
    }

    public void setAllEnrollments(List<Enrollment> allEnrollments) {
        this.allEnrollments = allEnrollments;
    }

    public List<Enrollment> getFilteredEnrollments() {
        return filteredEnrollments;
    }

    public void setFilteredEnrollments(List<Enrollment> filteredEnrollments) {
        this.filteredEnrollments = filteredEnrollments;
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

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
        setCourseEnrollments(enrollmentService.getAllEnrollmentsByCourseID(selectedCourse.getId()));
    }

    public List<Enrollment> getCourseEnrollments() {
        return courseEnrollments;
    }

    public void setCourseEnrollments(List<Enrollment> courseEnrollments) {
        this.courseEnrollments = courseEnrollments;
    }

    public void acceptEnrollment() {
        if (session.getUser() != null) {
            User manager = session.getUser();
            enrollmentService.approveEnrollment(selectedEnrollment, manager);
        }
    }

    public void declineEnrollment() {
        if (session.getUser() != null) {
            User manager = session.getUser();
            enrollmentService.rejectEnrollment(selectedEnrollment, manager, "");
        }
    }

    public void deleteEnrollment() {
        enrollmentService.deleteEnrollment(selectedEnrollment);
    }
}
