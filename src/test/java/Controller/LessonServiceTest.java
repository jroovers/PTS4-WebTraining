/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Course;
import Model.Lesson;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jorian
 */
public class LessonServiceTest {
    
    private Course c1;
    private Course c2;
    
    private Lesson l1;
    private Lesson l2;
    
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
        
        lessonService = new LessonService();
        location = "eindhoven";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        startTime = new GregorianCalendar();
        endTime = new GregorianCalendar();
        
        l1.setLocation(location);
        l1.setStartTime(startTime);
        l1.setEndTime(endTime);
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
     * Test of addLesson method, of class LessonService.
     */
    @Test
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
     * Test of getLessonsFromCourse method, of class LessonService.
     */
    @Test
    public void testGetLessonsFromCourse() throws Exception {
        System.out.println("getLessonsFromCourse");
        List<Lesson> result = lessonService.getLessonsFromCourse(c1.getId());
        assertTrue(!result.isEmpty());
    }
    
}