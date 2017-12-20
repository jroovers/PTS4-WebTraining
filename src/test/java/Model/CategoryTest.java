/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kyle_
 */
public class CategoryTest {
    
    Category cat1;  // ID : 25, Description : Javascript.
    Category cat2;  // ID : 0, Description : PHP.
    Category cat3;  // ID : -1, Description : HTML.
    Category cat4;  // ID : -1, Description : HTML.
    
    public CategoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cat1 = new Category(25,"Javascript");
        cat2 = new Category("PHP");
        cat3 = new Category(-1, "HTML");
        cat4 = new Category();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Category.
     * Test if the result ID equals the cat1 ID. 
     */
    @Test
    public void testGetId1() {
        System.out.println("getId 1");
        long result = 25;
        assertEquals(result, cat1.getId());
    }
    
    /**
     * Test of getId method, of class Category.
     * Test if the result ID equals the cat1 ID. 
     */
    @Test
    public void testGetId2() {
        System.out.println("getId 2");
        long result = 0;
        assertEquals(result, cat2.getId());
    }
    
    /**
     * Test of getId method, of class Category.
     * Test if a user can insert a negative ID in the Category. The number should
     * be changed to 0;
     */
    @Test
    public void testGetId3() {
        System.out.println("getId 3");     
        long result = 0;
        assertEquals(result, cat3.getId());
        Assert.assertNotEquals(-1, cat3.getId());
    }
    

    /**
     * Test of setId method, of class Category.
     * Test if ID from the category can be changed.
     */
    @Test
    public void testSetId1() {
        System.out.println("setId 1");
        long result = 26;
        cat1.setId(26);
        assertEquals(result, cat1.getId());
    }
    
    /**
     * Test of setId method, of class Category.
     * Test if ID from the category can be changed.
     */
    @Test
    public void testSetId2() {
        System.out.println("setId 2");
        long result = 0;
        cat1.setId(0);
        assertEquals(result, cat1.getId());
    }
    
    /**
     * Test of setId method, of class Category.
     * Test if ID from the category can be changed to a negative number. This
     * should not be possible.
     */
    @Test
    public void testSetId3() {
        System.out.println("setId 3");
        long result = 0;
        cat1.setId(-1);
        assertEquals(result, cat1.getId());
        Assert.assertNotEquals(-1, cat3.getId());
    }

    /**
     * Test of getName method, of class Category.
     * Test if the name of a category will be returned.
     */
    @Test
    public void testGetName1() {
        System.out.println("getName 1");
        String expResult = "Javascript";
        String result = cat1.getName();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of getName method, of class Category.
     * Test if the name of a category will be returned.
     */
    @Test
    public void testGetName2() {
        System.out.println("getName 2");
        String expResult = "PHP";
        String result = cat2.getName();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of getName method, of class Category.
     * Test if the name of a category will be returned.
     */
    @Test
    public void testGetName3() {
        System.out.println("getName 3");
        String expResult = "HTML";
        String result = cat3.getName();
        assertEquals(expResult, result);

    }

    /**
     * Test of setName method, of class Category.
     * Test if the name of a category can be changed.
     */
    @Test
    public void testSetName1() {
        System.out.println("setName 1");
        String expResult = "CSS";
        cat1.setName("CSS");
        String result = cat1.getName();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setName method, of class Category.
     * Test if the name of a category can be changed to "". This should not be
     * possible and it will keep the previous value. 
     */
    @Test
    public void testSetName2() {
        System.out.println("setName 2");
        String expResult = "Javascript";
        cat1.setName("");
        String result = cat1.getName();
        assertEquals(expResult, result);
    }
    
}
