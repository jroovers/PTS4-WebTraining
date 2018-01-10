/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance.Interfaces;

import Model.Enrollment;
import Model.User;
import java.util.List;

/**
 *
 * @author Jeroen Roovers
 */
public interface IEnrollmentDAO {

    /**
     * Adds a new enrollment to the database
     *
     * @param e enrollment to add.
     * @return enrollment with id set
     */
    public Enrollment addEnrollment(Enrollment e);

    /**
     * Get all enrollments
     *
     * @return list of all enrollments
     */
    public List<Enrollment> getAllEnrollments();

    /**
     * Gets all enrollments on a certain course. This means all the enrollments
     * into all lessons of that course.
     *
     * @return list of all enrollsments with a lesson of given course
     */
    public List<Enrollment> getAllEnrollmentsByCourseID();

    /**
     * Gets all the enrollments in the database with the given status.
     *
     * @param status in databse 0=unhandled 1=accepted 2=rejected
     * @return list of all enrollments with given status
     */
    public List<Enrollment> getAllEnrollmentsByStatus(int status);

    /**
     * Edits an exisiting enrollment in the database.
     *
     * @param e enrollment to update
     * @return true if successful
     */
    public boolean editEnrollment(Enrollment e);

    /**
     * Approves the given enrollment
     *
     * @param e enrollment to approve
     * @param manager manager that approves
     * @return true if succesful
     */
    public boolean approveEnrollment(Enrollment e, User manager);

    /**
     * Rejects the given enrollment
     *
     * @param e enrollment to reject
     * @param manager manager that rejects
     * @param comment comment/info to go along with rejection
     * @return true if succesfully rejected
     */
    public boolean rejectEnrollment(Enrollment e, User manager, String comment);

    /**
     * Removes an exisiting enrollment in the database.
     *
     * @param e enrollment to delete
     * @return true if successful
     */
    public boolean deleteEnrollment(Enrollment e);

}
