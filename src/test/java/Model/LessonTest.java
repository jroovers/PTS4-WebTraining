package Model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricardo van Dijke
 */
public class LessonTest
{

    Lesson lesson;
    Course course;

    @Before
    public void setUp()
    {
        course = new Course("banaan", "appel");
        course.setId(2);
        lesson = new Lesson(course);
    }

    /**
     * Test of getId and setId method, of class Lesson.
     */
    @Test
    public void testID()
    {
        //arrange
        long expResult = 2L;

        //act
        lesson.setId(expResult);
        long result = lesson.getId();

        //assert
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartTime and setStartTime method, of class Lesson.
     */
    @Test
    public void testStartTime()
    {
        //arrange
        GregorianCalendar startTime = new GregorianCalendar();

        startTime.setTime(new Date());

        //act
        lesson.setStartTime(startTime);
        Calendar result = lesson.getStartTime();

        //assert
        assertEquals(startTime, result);
    }

    /**
     * Test of getEndTime and setEndTime method, of class Lesson.
     */
    @Test
    public void testEndTime()
    {
        //arrange
        GregorianCalendar endTime = new GregorianCalendar();

        endTime.setTime(new Date());

        //act
        lesson.setEndTime(endTime);
        Calendar result = lesson.getEndTime();

        //assert
        assertEquals(endTime, result);
    }

    /**
     * Test of getLocation and setLocation method, of class Lesson.
     */
    @Test
    public void testLocation()
    {
        //arrange
        String expResult = "Eindhoven";

        //act
        lesson.setLocation(expResult);
        String result = lesson.getLocation();

        //assert
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourse and setCourse method, of class Lesson.
     */
    @Test
    public void testCourse()
    {
        //arrange
        Course expResult = new Course("001", "testname");

        //act
        lesson.setCourse(expResult);
        Course result = lesson.getCourse();

        //assert
        assertEquals(expResult, result);
    }

    /**
     * Test of format method, of class Lesson.
     */
    @Test
    public void testFormat()
    {
        //arrange
        GregorianCalendar startTime = new GregorianCalendar();

        startTime.set(2017,11,12);

        //act
        lesson.setStartTime(startTime);
        Calendar cal = lesson.getStartTime();

        String expResult = "11-Dec-2017";
        String result = Lesson.format(cal);

        //assert
        assertEquals(expResult, result);
    }

    /**
     * Test of getLessonString method, of class Lesson.
     */
    @Test
    public void testGetLessonString()
    {
        //arrange
        GregorianCalendar startTime = new GregorianCalendar();

        startTime.setTime(new Date(2017, 12, 11));

        //act
        lesson.setStartTime(startTime);
        lesson.setLocation("Eindhoven");
        lesson.setCourse(course);
        String expResult = "11-dec-2017 - Eindhoven - appel";
        String result = lesson.getLessonString();
        assertEquals(expResult, result);
    }
}