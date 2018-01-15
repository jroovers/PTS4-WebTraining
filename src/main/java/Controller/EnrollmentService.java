/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Persistance.DbUtilsImpl.EnrollmentDAOUtilsImpl;
import Model.Enrollment;
import Model.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Jeroen, Antonio
 */
@Stateless
public class EnrollmentService {

    public List<Enrollment> getEnrollmentsByUser(int userID) {
        EnrollmentDAOUtilsImpl enrollmentDAO = new EnrollmentDAOUtilsImpl();

        return enrollmentDAO.GetEnrollmentsByUser(userID);
    }

    
    public List<Enrollment> getAllEnrollments() {
        EnrollmentDAOUtilsImpl enrollmentDAO = new EnrollmentDAOUtilsImpl();
        return enrollmentDAO.getAllEnrollments();        
    }

    public List<Enrollment> getAllEnrollmentsByCourseID(long courseID) {
        EnrollmentDAOUtilsImpl enrollmentDAO = new EnrollmentDAOUtilsImpl();
        return enrollmentDAO.getAllEnrollmentsByCourseID(courseID);        
    }
    
    public List<Enrollment> getAllEnrollmentsByStatus(int status) {
        EnrollmentDAOUtilsImpl enrollmentDAO = new EnrollmentDAOUtilsImpl();
        return enrollmentDAO.getAllEnrollmentsByStatus(status);        
    }
    
    public Enrollment addEnrollment(Enrollment enrollment) {
        EnrollmentDAOUtilsImpl enrollmentDAO = new EnrollmentDAOUtilsImpl();
        return enrollmentDAO.addEnrollment(enrollment);        
    }
    
    public boolean approveEnrollment(Enrollment enrollment, User manager) {
        EnrollmentDAOUtilsImpl enrollmentDAO = new EnrollmentDAOUtilsImpl();
        return enrollmentDAO.approveEnrollment(enrollment, manager);        
    }
    
    public boolean deleteEnrollment(Enrollment enrollment) {
        EnrollmentDAOUtilsImpl enrollmentDAO = new EnrollmentDAOUtilsImpl();
        return enrollmentDAO.deleteEnrollment(enrollment);        
    }
    
    public boolean editEnrollment(Enrollment enrollment) {
        EnrollmentDAOUtilsImpl enrollmentDAO = new EnrollmentDAOUtilsImpl();
        return enrollmentDAO.editEnrollment(enrollment);        
    }
    
    public boolean rejectEnrollment(Enrollment enrollment, User manager, String comment) {
        EnrollmentDAOUtilsImpl enrollmentDAO = new EnrollmentDAOUtilsImpl();
        return enrollmentDAO.rejectEnrollment(enrollment, manager, comment);        
    }
}
