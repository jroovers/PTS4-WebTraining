package Controller;

import Model.Course;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Jorian
 */
@Ignore
public class CourseServiceTest {

    CourseService courseService = new CourseService();
    private Course c1;
    private Course c2;

       
    @Before
    public void setUp() {
        String[] testArray = new String[2];
        String testSupplier = "InfoSupport";
        testArray[0] = "Jemam";
        testArray[1] = "Jepap";
        c1 = new Course(33, "UnitTest1", "name", "textDescription", testArray, "courseMaterials", testArray, 3, 1.1);
        c2 = new Course(33, "UnitTest1", "name", "textDescription", testArray, "courseMaterials", testArray, 3, 1.1);
        c1.setSupplier(testSupplier);
        c2.setSupplier(testSupplier);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addCourse method, of class CourseService.
     */
//    //@Test
//    public void testAddCourse_String_String() throws Exception {
//        System.out.println("addCourse with code and name");
//        
//        Course expResult = c2;
//        Course result = courseService.addCourse(c1.getCode(), c1.getName());
//        
//        //Test of de course een id heeft gekregen in de database.
//        assertNotNull(result.getId());
//        
//        //Test of de codes en namen gelijk zijn.
//        assertEquals(expResult.getCode(), result.getCode());
//        assertEquals(expResult.getName(), result.getName());
//        
//        courseService.removeCourse(result.getId());
//    }

    /**
     * Test of addCourse method, of class CourseService.
     */
    @Test
    public void testAddCourse_Course() throws Exception {
        System.out.println("addCourse");
        Course expResult = c2;
        Course result = courseService.addCourse(c1);
        assertEquals(expResult.getCode(), result.getCode());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getDescription(), result.getDescription());
        Assert.assertArrayEquals(expResult.getPriorKnowledge(), result.getPriorKnowledge());
        assertEquals(expResult.getCourseMaterials(), result.getCourseMaterials());
        Assert.assertArrayEquals(expResult.getKeyWords(), result.getKeyWords());
        assertEquals(expResult.getDurationInDays(), result.getDurationInDays());
        assertEquals(expResult.getCost(), result.getCost(), 0.00);
        
        courseService.removeCourse(result.getId());
    }

    /**
     * Test of getAllCourses method, of class CourseService.
     */
    @Test
    public void testGetAllCourses() throws Exception {
        System.out.println("getAllCourses");
        List<Course> result = courseService.getAllCourses();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of editCourse method, of class CourseService.
     */
    @Test
    public void testEditCourse() throws Exception {
        System.out.println("editCourse");
        boolean expResult = true;
        boolean result = courseService.editCourse(c1);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeCourse method, of class CourseService.
     */
    @Test
    public void testRemoveCourse() throws Exception {
        System.out.println("removeCourse");
        boolean expResult = true;
        Course c3 = courseService.addCourse(c1);
        boolean result = courseService.removeCourse(c3.getId());
        assertEquals(expResult, result);
    }
    
}
