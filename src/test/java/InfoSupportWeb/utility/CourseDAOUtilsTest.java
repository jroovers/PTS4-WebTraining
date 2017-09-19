/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import Model.Course;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jorian
 */
public class CourseDAOUtilsTest {
    
    private CourseDAOUtils cDAO;
    
    public CourseDAOUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.cDAO = new CourseDAOUtils();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCourses method, of class CourseDAOUtils.
     */
    @org.junit.Test
    public void testGetCourses() {
    }

    /**
     * Test of addCourse method, of class CourseDAOUtils.
     */
    @org.junit.Test
    public void testAddCourse() {
        System.out.println("addCourse");
        Course course = new Course("12345", "newCourse");
        cDAO.addCourse(course);
//        Course expResult = null;
//        Course result = instance.addCourse(course);
//        assertEquals(expResult, result);
    }

    /**
     * Test of editCourse method, of class CourseDAOUtils.
     */
    @org.junit.Test
    public void testEditCourse() {
        System.out.println("editCourse");
        Course course = null;
        CourseDAOUtils instance = new CourseDAOUtils();
        boolean expResult = false;
        boolean result = instance.editCourse(course);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCourse method, of class CourseDAOUtils.
     */
    @org.junit.Test
    public void testRemoveCourse() {
        System.out.println("removeCourse");
        Course course = null;
        CourseDAOUtils instance = new CourseDAOUtils();
        boolean expResult = false;
        boolean result = instance.removeCourse(course);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
