/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Course;
import Model.Enrollment;
import Model.EnrollmentTest;
import Model.Lesson;
import Model.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antonio
 */
public class EnrollmentServiceTest {

    EnrollmentService es = new EnrollmentService();
    User student;
    User student2;
    User manager;
    Lesson lesson;
    Enrollment e1;
    Enrollment e2;
    Course course;

    @Before
    public void CreateTestUser() {
        student = new User(1, "Frank", "franken", "Frankster", "frankisthebest", "001234", "Frankster@TheG.com");
        student.addAccessLevel(1);
        student2 = new User(4, "Bartel-Jaap", "van Rundfunk", "BJ", "barteljaap", "004312321", "Barteljaap@msn.com");
        student2.addAccessLevel(1);
        manager = new User(3, "Kyle", "Raaij", "Bender", "Bender", "09000900", "KyleVanRaaij@gmail.com");
        manager.addAccessLevel(3);
        course = new Course(1, "00322", "Onderzoek", "Workshop Onderzoek", null, "Presentatie", 2, 90);
        lesson = new Lesson(course);
        lesson.setId(2L);
        e1 = new Enrollment(lesson, student);
        e2 = new Enrollment(lesson, student2);
        e1.setId(1L);
        e2.setId(2L);
    }

    /**
     * Test of getAllEnrollments method, of class EnrollmentService.
     */
    @Test
    public void testGetAllEnrollments() throws Exception {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetAllEnrollments");
        List<Enrollment> enrollments = es.getAllEnrollments();

        assertNotNull(enrollments);
    }

    /**
     * Test of getAllEnrollmentsByCourseID method, of class EnrollmentService.
     */
    @Test
    public void testGetAllEnrollmentsByCourseID() throws Exception {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetAllEnrollmentsByCourseID");
        e1.setId(4L);
        List<Enrollment> enrollments = es.getAllEnrollmentsByCourseID(e1.getId());

        assertNotNull(enrollments);
        
         //Unhappy flow testing
        e1.setId(-1L);
        List<Enrollment> badEnrollments = es.getAllEnrollmentsByCourseID(e1.getId());
        assertEquals(0, badEnrollments.size());
    }

    /**
     * Test of getAllEnrollmentsByStatus method, of class EnrollmentService.
     */
    @Test
    public void testGetAllEnrollmentsByStatus() throws Exception {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetAllEnrollmentsByStatus");
        List<Enrollment> enrollments;
        enrollments = es.getAllEnrollmentsByStatus(1);
        assertNotNull(enrollments);
        enrollments = null;
        enrollments = es.getAllEnrollmentsByStatus(2);
        assertNotNull(enrollments);
        
        //Unhappy flow testing
        e1.setId(0L);
        List<Enrollment> badEnrollments = es.getAllEnrollmentsByStatus(-1);
        assertEquals(0, badEnrollments.size());
    }

    /**
     * Test of addEnrollment method, of class EnrollmentService.
     */
    @Test
    public void testAddEnrollment() throws Exception {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testAddEnrollment");
        Enrollment enrollment = es.addEnrollment(e1);
        assertEquals(enrollment.getId(), e1.getId());
        
        boolean expected = es.deleteEnrollment(enrollment);
        assertEquals(true, expected);
        
        //Unhappy flow testing
        lesson.setId(0L);
        e1.setLesson(lesson);
        Enrollment badEnrollment = es.addEnrollment(e1);
        assertNull(badEnrollment);
    }

    /**
     * Test of approveEnrollment method, of class EnrollmentService.
     */
    @Test
    public void testApproveEnrollment() throws Exception {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testApproveEnrollment");
        Enrollment enrollment = es.addEnrollment(e1);
        boolean expected = es.approveEnrollment(enrollment, manager);
        assertEquals(true, expected);
        
        boolean expectedDeletion = es.deleteEnrollment(enrollment);
        assertEquals(true, expectedDeletion);
        
        //Unhappy flow testing
        e1.setId(0L);
        boolean badEnrollment = es.approveEnrollment(e1, manager);
        assertFalse(badEnrollment);
    }

    /**
     * Test of deleteEnrollment method, of class EnrollmentService.
     */
    @Test
    public void testDeleteEnrollment() throws Exception {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testDeleteEnrollment");
        Enrollment enrollment = es.addEnrollment(e1);
        assertEquals(enrollment.getId(), e1.getId());
        
        boolean expected = es.deleteEnrollment(enrollment);
        assertEquals(true, expected);
        
        //Unhappy flow testing
        e1.setId(0L);
        boolean badEnrollment = es.deleteEnrollment(e1);
        assertFalse(badEnrollment);
    }

    /**
     * Test of editEnrollment method, of class EnrollmentService.
     */
//    @Test
//    public void testEditEnrollment() throws Exception {
//        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testEditEnrollment");
//        Enrollment enrollment = es.addEnrollment(e1);
//        boolean expected = es.editEnrollment(enrollment);
//        assertEquals(true, expected);
//        
//        boolean expectedDeletion = es.deleteEnrollment(enrollment);
//        assertEquals(true, expectedDeletion);
//    
//    //Unhappy flow testing
//        e1.setId(0L);
//        boolean badEnrollment = es.editEnrollment(e1);
//        assertNull(badEnrollment);
//    }

    /**
     * Test of rejectEnrollment method, of class EnrollmentService.
     */
    @Test
    public void testRejectEnrollment() throws Exception {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testRejectEnrollment");
        Enrollment enrollment = es.addEnrollment(e1);
        boolean expected = es.rejectEnrollment(enrollment, manager, "Reject Test");
        assertEquals(true, expected);
        
        boolean expectedDeletion = es.deleteEnrollment(enrollment);
        assertEquals(true, expectedDeletion);
        
        //Unhappy flow testing
        e1.setId(0L);
        boolean badEnrollment = es.rejectEnrollment(e1, manager, "Reject Test");
        assertFalse(badEnrollment);
    }
}
