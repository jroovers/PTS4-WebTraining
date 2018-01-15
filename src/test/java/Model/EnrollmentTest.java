/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antonio
 */
public class EnrollmentTest {

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
        lesson.setId(1L);
        e1 = new Enrollment(lesson, student);
        e2 = new Enrollment(lesson, student2);
        e1.setId(1L);
        e2.setId(2L);
    }

    /**
     * Test of Approve method, of class Enrollment.
     */
    @Test
    public void testApprove() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testApprove with Manager as object");
        String expectedManagerName = manager.getName();
        e1.Approve(manager);;
        assertEquals(expectedManagerName, e1.getManager().getName());
    }

    /**
     * Test of Reject method, of class Enrollment.
     */
    @Test
    public void testReject() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testReject with Manager as object");
        String expectedManagerName = manager.getName();
        e1.Reject(manager);;
        assertEquals(expectedManagerName, e1.getManager().getName());
    }

    /**
     * Test of getStatus method, of class Enrollment.
     */
    @Test
    public void testGetStatus() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetStatus without parameters");
        String expectedReturn = "in afwachting";
        assertEquals(expectedReturn, e1.getStatus());
        expectedReturn = "afgewezen";
        e1.Reject(manager);
        assertEquals(expectedReturn, e1.getStatus());
        expectedReturn = "akkoord";
        e2.Approve(manager);
        assertEquals(expectedReturn, e2.getStatus());
        expectedReturn = "Fout: akkoord EN afgewezen";
        e1.setAccepted(true);
        assertEquals(expectedReturn, e1.getStatus());
    }

    /**
     * Test of getId method, of class Enrollment.
     */
    @Test
    public void testGetSetId() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetID and testSetID");
        long expectedID = e1.getId();
        assertEquals(1L, expectedID);
        e1.setId(3L);
        assertEquals(3L, e1.getId());
    }

    /**
     * Test of getLesson method, of class Enrollment.
     */
    @Test
    public void testGetLesson() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetLesson and testSetLesson");
        e1.setLesson(lesson);
        long expectedLessonID = e1.getLesson().getId();
        assertEquals(expectedLessonID, 1L);
    }

    /**
     * Test of getStudent method, of class Enrollment.
     */
    @Test
    public void testGetStudent() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetStudent and testSetStudent");
        e1.setStudent(student);
        String expectedStudentName = e1.getStudent().getName();
        assertEquals(expectedStudentName, "Frank");
    }

    /**
     * Test of getSignupTime method, of class Enrollment.
     */
    @Test
    public void testGetSignupTime() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetSignupTime and testSetSignupTime");
        Calendar calendar = Calendar.getInstance();
        e1.setSignupTime(calendar);
        assertEquals(calendar, e1.getSignupTime());
    }

    /**
     * Test of getManager method, of class Enrollment.
     */
    @Test
    public void testGetSetManager() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetManager and testSetManager");
        e1.setManager(manager);
        String expectedManagerName = manager.getName();
        assertEquals(expectedManagerName, e1.getManager().getName());
    }

    /**
     * Test of setManager method, of class Enrollment.
     */
    @Test
    public void testGetSetApprovalTime() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetApprovalTime and testSetApprovalTime");
        Calendar calendar = Calendar.getInstance();
        e1.setApprovalTime(calendar);
        assertEquals(calendar, e1.getApprovalTime());
    }

    /**
     * Test of isAccepted method, of class Enrollment.
     */
    @Test
    public void testIsAccepted() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testIsAccepted");
        e1.setAccepted(true);
        assertEquals(true, e1.isAccepted());
    }

    /**
     * Test of isRejected method, of class Enrollment.
     */
    @Test
    public void testIsRejected() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testIsRejected");
        e1.setRejected(true);
        assertEquals(true, e1.isRejected());
    }

    /**
     * Test of getComment method, of class Enrollment.
     */
    @Test
    public void testGetSetComment() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetComment and testSetComment");
        e1.setComment("Test1");
        assertEquals("Test1", e1.getComment());
    }

    /**
     * Test of hashCode method, of class Enrollment.
     */
    @Test
    public void testHashCode() {
        Logger.getLogger(EnrollmentTest.class.getName()).log(Level.INFO, "testGetComment and testSetComment");
        //Enrollment e3 = new Enrollment(null, null);
        //Enrollment e4 = new Enrollment(null, null);
        //assertTrue(e3.equals(e4) && e4.equals(e3));
        //assertTrue(e3.hashCode() == e4.hashCode());
        EqualsVerifier.forClass(Enrollment.class).usingGetClass().suppress(Warning.NONFINAL_FIELDS).verify();
    }

//    /**
//     * Test of equals method, of class Enrollment.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object obj = null;
//        Enrollment instance = null;
//        boolean expResult = false;
//        boolean result = instance.equals(obj);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
