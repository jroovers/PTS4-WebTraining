/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Course;
import Model.Lesson;
import Model.User;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class LessonServiceTest {
    
    private Course c1;
    private Course c2;
    
    private Lesson l1;
    private Lesson l2;
    
    private User u1;
    private User u2;
    
    private Calendar startTime;
    private Calendar endTime;
    
    private String location;
    
    private LessonService lessonService;
    
    public LessonServiceTest() {
    }
    
    
    @Before
    public void setUp() {
        c1 = new Course("banaan", "appel");
        c1.setId(2);
        l1 = new Lesson(c1);
        l2 = new Lesson(c1);
        u1 = new User("Frank", "franken", "001234", "Frankster@TheG.com", 2);
        u2 = new User("Bert", "bertus", "004321", "BertusThebertustest@banana.com", 1);
        
        lessonService = new LessonService();
        location = "eindhoven";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        startTime = new GregorianCalendar();
        endTime = new GregorianCalendar();
        
        l1.setLocation(location);
        l1.setStartTime(startTime);
        l1.setEndTime(endTime);
        l1.setTeacher(u1);
        l1.setId(1);
        
        l2.setLocation(location);
        l2.setStartTime(startTime);
        l2.setEndTime(endTime);
        l2.setId(1);
    }
    
    @After
    public void tearDown() {
    }

   

    /**
     * Test of getLessons method, of class LessonService.
     */
    @Test
    public void testGetLessons() throws Exception {
        System.out.println("getLessons");
        List<Lesson> result = lessonService.getLessons();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of editLesson method, of class LessonService.
     */
    @Test
    public void testEditLesson() throws Exception {
        System.out.println("editLesson");
        boolean expResult = true;
        boolean result = lessonService.editLesson(l1);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteLesson method, of class LessonService.
     */
    @Test
    public void testDeleteLesson() throws Exception {
        System.out.println("deleteLesson");
        boolean expResult = true;
        l1.setId(1);
        boolean result = lessonService.deleteLesson(l1.getId());
        assertEquals(expResult, result);
    }
    
     /**
     * Test of addLesson method, of class LessonService.
     */
    @Test
    @Ignore
    public void testAddLesson_Lesson() throws Exception {
        System.out.println("addLesson");
        Lesson expResult = l1;
        Lesson result = lessonService.addLesson(l1);
        assertNotNull(result.getId());
        assertEquals(expResult.getLessonString(), result.getLessonString());
        assertEquals(expResult.getLocation(), result.getLocation());
        assertEquals(expResult.getStartTime(), result.getStartTime());
    }

    /**
     * Test of getLessonsFromCourse method, of class LessonService.
     */
    @Test
    public void testGetLessonsFromCourse() throws Exception {
        System.out.println("getLessonsFromCourse");
        List<Lesson> result = lessonService.getLessonsFromCourse(c1.getId());
        assertTrue(!result.isEmpty());
    }
    
    @Test
    public void testGetLessonsAndRegistrationsByTeacher() throws Exception {
        System.out.println("getLessonsAndRegistrationsByTeacher");
        u1.setUserID(1);

        List<Lesson> result = lessonService.GetLessonsAndRegistrationsByTeacher(u1.getUserID());
        assertTrue(!result.isEmpty());
    }
    
    @Test
    public void testGetLessonsAndRegisterationByEmail() throws Exception {
        System.out.println("getLessonsAndRegistrationBijEmail");
        List<Lesson> result = lessonService.GetLessonsAndRegistrationsByEmail(u1.getEmail());
        assertTrue(!result.isEmpty());
    }
    
    @Test
    public void testSingUpUser(){
        System.out.println("signUpUser");
        long result = lessonService.signUpUser(l1.getId(), u1.getUserID());
        assertNotEquals(null, result);
    }
    
    
}
