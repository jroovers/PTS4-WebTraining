package Model;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Ricardo van Dijke
 */
public class CourseTest {

    private Course course;
    private Course course2;

    @Before
    public void CreateTestCourse() {
        course2 = new Course();
        course = new Course(
                1,
                "001",
                "Workshop pannenkoeken bakken",
                "Hier leer je pannenkoeken bakken",
                new String[]{"Bakken 101", "Bakken 102"},
                "Pan, Meel",
                new String[]{"Pannenkoeken", "Bakken"},
                5,
                10
        );
    }

    /**
     * Test of setId method, of class Course.
     */
    @Test
    public void testSetId() {
        //arrange
        long expResult = 10;

        //act
        course.setId(expResult);
        long result = course.getId();

        //assert
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Course.
     */
    @Test
    public void testGetId() {
        //arrange
        long expResult = 1;

        //act
        course.setId(expResult);
        long result = course.getId();

        //assert
        assertEquals(expResult, result);
    }

    /**
     * Test of getCode method, of class Course.
     */
    @Test
    public void testGetCode() {
        //arrange
        String expResult = "001";
        //act
        String result = course.getCode();
        //assert
        assertEquals(expResult, result);
    }

    /**
     * Test of setCode method, of class Course.
     */
    @Test
    public void testSetCode() {
        //arrange
        String code;
        String expCode = "002";
        //act
        course.setCode(expCode);

        code = course.getCode();

        //assert
        assertEquals(expCode, code);
    }

    /**
     * Test of getName method, of class Course.
     */
    @Test
    public void testGetName() {
        //arrange
        String expName = "Workshop pannenkoeken bakken";
        //act
        String result = course.getName();
        //assert
        assertEquals(expName, result);
    }

    /**
     * Test of setName method, of class Course.
     */
    @Test
    public void testSetName() {
        //arrange
        String name;
        String expName = "Workshop Koekjes bakken";
        //act
        course.setName(expName);

        name = course.getName();

        //assert
        assertEquals(expName, name);
    }

    /**
     * Test of getDescription method, of class Course.
     */
    @Test
    public void testGetDescription() {
        //arrange
        String expDescription = "Hier leer je pannenkoeken bakken";
        //act
        String result = course.getDescription();
        //assert
        assertEquals(expDescription, result);
    }

    /**
     * Test of setDescription method, of class Course.
     */
    @Test
    public void testSetDescription() {
        //arrange
        String description;
        String expDescription = "002";
        //act
        course.setDescription(expDescription);

        description = course.getDescription();

        //assert
        assertEquals(expDescription, description);
    }

    /**
     * Test of getPriorKnowledge method, of class Course.
     */
    @Test
    public void testGetPriorKnowledge() {
        //arrange
        String[] expPriorKnowledge = {"Bakken 101", "Bakken 102"};
        //act
        String[] result = course.getPriorKnowledge();
        //assert
        Assert.assertArrayEquals(expPriorKnowledge, result);
    }

    /**
     * Test of setPriorKnowledge method, of class Course.
     */
    @Test
    public void testSetPriorKnowledge() {
        //arrange
        String[] priorKnowledge;
        String[] expPriorKnowledge = {"PriorKnowledge1", "PriorKnowledge2"};
        //act
        course.setPriorKnowledge(expPriorKnowledge);

        priorKnowledge = course.getPriorKnowledge();

        //assert
        Assert.assertArrayEquals(expPriorKnowledge, priorKnowledge);
    }
    
    /**
     * Test of setPriorKnowledge method, of class Course.
     */
    @Test
    public void testSetPriorKnowledge2() {
        //arrange
        String[] priorKnowledge;
        String[] expPriorKnowledge = new String[]{"Niet geladen"};

        priorKnowledge = course2.getPriorKnowledge();

        //assert
        Assert.assertArrayEquals(expPriorKnowledge, priorKnowledge);
    }

    /**
     * Test of getCourseMaterialsmethod, of class Course.
     */
    @Test
    public void testGetCourseMaterials() {
        //arrange
        String expCourseMaterials = "Pan, Meel";
        //act
        String result = course.getCourseMaterials();
        //assert
        assertEquals(expCourseMaterials, result);
    }

    /**
     * Test of setCourseMaterials method, of class Course.
     */
    @Test
    public void testSetCourseMaterials() {
        //arrange
        String courseMaterials;
        String expCourseMaterials = "Meel, Pan";
        //act
        course.setCourseMaterials(expCourseMaterials);

        courseMaterials = course.getCourseMaterials();

        //assert
        assertEquals(expCourseMaterials, courseMaterials);
    }

    /**
     * Test of getKeyWords method, of class Course.
     */
    @Test
    public void testGetKeyWords() {
        //arrange
        String[] expKeyWords = {"Pannenkoeken", "Bakken"};
        //act
        String[] result = course.getKeyWords();
        //assert
        Assert.assertArrayEquals(expKeyWords, result);
    }

    /**
     * Test of setKeyWords method, of class Course.
     */
    @Test
    public void testSetKeyWords() {
        //arrange
        String[] keyWords;
        String[] expKeyWords = {"Keyword1", "Keyword2"};
        //act
        course.setKeyWords(expKeyWords);

        keyWords = course.getKeyWords();

        //assert
        Assert.assertArrayEquals(expKeyWords, keyWords);
    }

    /**
     * Test of getDurationInDays, of class Course.
     */
    @Test
    public void testGetDurationInDays() {
        //arrange
        int durationInDays = 5;
        //act
        int result = course.getDurationInDays();
        //assert
        assertEquals(durationInDays, result);
    }

    /**
     * Test of setDurationInDays method, of class Course.
     */
    @Test
    public void testSetDurationInDays() {
        //arrange
        int durationInDays;
        int expDurationInDays = 11;
        //act
        course.setDurationInDays(expDurationInDays);

        durationInDays = course.getDurationInDays();

        //assert
        assertEquals(expDurationInDays, durationInDays);
    }

    /**
     * Test of getCost, of class Course.
     */
    @Test
    public void testGetCourse() {
        //arrange
        double cost = 10;
        //act
        double result = course.getCost();
        //assert
        assertEquals(cost, result, 0.05);
    }
    
    /**
     * Test of getSupplier, of class Course.
     */
    @Test
    public void testGetSupplier() {
        course.setSupplier("PostNL");
        String result = course.getSupplier();
        
        assertEquals("PostNL", result);
    }
    
    /**
     * Test of getSupplier, of class Course.
     */
    @Test
    public void testSetSupplier() {
        course.setSupplier("PostNL");
        String result = course.getSupplier();
        
        assertEquals("PostNL", result);
    }

    /**
     * Test of setCost method, of class Course.
     */
    @Test
    public void testSetCost() {
        //arrange
        double cost;
        double expCost = 11;
        //act
        course.setCost(expCost);

        cost = course.getCost();

        //assert
        assertEquals(expCost, cost, 0.05);
    }
    
    /**
     * Test of getShortString method, of class Course.
     */
    @Test
    public void testGetShortString() {    
        Assert.assertNotNull(course.getShortString());
    }
    
    /**
     * Test of getLongString method, of class Course.
     */
    @Test
    public void testGetLongString() {    
        Assert.assertNotNull(course.getLongString());
    }
    
    @Test
    public void testConstructor1() {
        //arrange
        long id;
        String code;
        String name;
        String description;
        String[] priorKnowledge;
        String courseMaterials;
        int durationInDays;
        double cost;

        Course newCourse;

        //act
        id = 20L;
        code = "PS1";
        name = "Workshop Presenteren";
        description = "Hier leer je hoe je fatsoenlijk presenteert";
        priorKnowledge = new String[]{"Helemaal niks"};
        courseMaterials = "Laptop";
        durationInDays = 4;
        cost = 15.50;

        newCourse = new Course(id, code, name, description, priorKnowledge, courseMaterials, durationInDays, cost);

        assertEquals(id, newCourse.getId());
        assertEquals(code, newCourse.getCode());
        assertEquals(name, newCourse.getName());
        assertEquals(description, newCourse.getDescription());
        Assert.assertArrayEquals(priorKnowledge, newCourse.getPriorKnowledge());
        assertEquals(courseMaterials, newCourse.getCourseMaterials());
        assertEquals(durationInDays, newCourse.getDurationInDays());
        assertEquals(cost, newCourse.getCost(),0.01);
    }
}
