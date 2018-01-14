/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Persistance.DbUtilsImpl.EnrollmentDAOUtilsImpl;
import Model.Enrollment;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Jeroen
 */
@Stateless
public class EnrollmentService {

    public List<Enrollment> getAllEnrollments() {
        EnrollmentDAOUtilsImpl enrollmentDAO = new EnrollmentDAOUtilsImpl();
        return enrollmentDAO.getAllEnrollments();
    }

}
