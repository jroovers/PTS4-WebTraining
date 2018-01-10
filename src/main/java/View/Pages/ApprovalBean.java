package View.Pages;

import Model.Course;
import Model.Enrollment;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Jeroen Roovers
 */
@Named(value = "approvalBean")
@RequestScoped
public class ApprovalBean {

    private Enrollment selectedEnrollment;
    private List<Enrollment> allEnrollments;
    private List<Enrollment> filteredEnrollments;
    private Course selectedCourse;
    private List<Course> courses;
    private List<Course> filteredCourses;

    /**
     * Creates a new instance of ApprovalBean
     */
    public ApprovalBean() {
    }

    /**
     * TODO
     *
     * @return
     */
    public String onAcceptEnrollment() {
        return null;
    }

    /**
     * TODO
     *
     * @return
     */
    public String onRejectEnrollment() {
        return null;
    }

    /**
     *
     * @return
     */
    public String onDeleteEnrollment() {
        return null;
    }

    public Enrollment getSelectedEnrollment() {
        return selectedEnrollment;
    }

    public void setSelectedEnrollment(Enrollment selectedEnrollment) {
        this.selectedEnrollment = selectedEnrollment;
    }

    public List<Enrollment> getAllEnrollments() {
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
    }
    
    public void acceptEnrollment() {
        //TODO: DAO laag aanroepen om Enrollment te accepteren
        
    }
    
    public void declineEnrollment() {
        //TODO: DAO laag aanroepen om Enrollment te weigeren
        
    }
}
