/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Category;
import Model.Course;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antonio
 */
public class CategoryServiceTest {

    CategoryService categoryService = new CategoryService();
    private Category c1;
    private Category c2;
    private Course course;

    public CategoryServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        c1 = new Category(1, "UIX");
        c2 = new Category(98, "Test2");

        //course
        String[] testArray = new String[2];
        String testSupplier = "InfoSupport";
        testArray[0] = "testcase1";
        testArray[1] = "testcase2";
        course = new Course(119, "UnitTest1", "name", "textDescription", testArray, "courseMaterials", testArray, 3, 1.1);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addCategory method, of class CategoryService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddCategory() throws Exception {
        Logger.getLogger(CategoryServiceTest.class.getName()).log(Level.INFO, "addCategory with the name of the Category as string");
        List<Category> categories = categoryService.getAllCategories();
        boolean result = categoryService.addCategory("Test2");
        List<Category> expCategories = categoryService.getAllCategories();
        assertEquals(true, result);
        assertEquals(categories.size() + 1, expCategories.size());
        categoryService.removeCategory_byName("Test2");
    }

    /**
     * Test of updateCategory method, of class CategoryService.
     *
     * @throws java.lang.Exception
     */
//    @Test
//    public void testUpdateCategory() throws Exception {
//        Logger.getLogger(CategoryServiceTest.class.getName()).log(Level.INFO, "updateCategory with an object Category");
//        Category c3 = new Category(7, "ASP.NET");
//        Category c4 = new Category(7, "BI");
//        boolean result = categoryService.updateCategory(c3);
//        assertEquals("1 - ", true, result);
//        categoryService.updateCategory(c4);
//    }

    /**
     * Test of getAllCategories method, of class CategoryService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllCategories() throws Exception {
        Logger.getLogger(CategoryServiceTest.class.getName()).log(Level.INFO, "getAllCategories with no parameters");
        List<Category> categories = categoryService.getAllCategories();
        assertNotNull(categories.size());
    }

    /**
     * Test of removeCategory method, of class CategoryService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveCategory() throws Exception {
        Logger.getLogger(CategoryServiceTest.class.getName()).log(Level.INFO, "removeCategory with no parameters");
        boolean result = categoryService.addCategory("Test1");
        List<Category> expCategories = categoryService.getAllCategories();
        assertEquals(true, result);
        Category c3 = new Category();
        for (Category cat : expCategories) {
            if (cat.getName().equals("Test1")) {
                c3.setId(cat.getId());
                c3.setName(cat.getName());
            }
        }
        boolean expResult = categoryService.removeCategory(c3);
        assertEquals(true, expResult);
        List<Category> actualCategories = categoryService.getAllCategories();
        assertEquals(actualCategories.size(), expCategories.size() - 1);
    }

    /**
     * Test of getCategoriesFromCourse method, of class CategoryService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCategoriesFromCourse_Course() throws Exception {
        Logger.getLogger(CategoryServiceTest.class.getName()).log(Level.INFO, "getCategoriesFromCourse_Course with a course object");
        List<Category> categories = categoryService.getCategoriesFromCourse(course);
        assertNotNull(categories.size());        
    }

    /**
     * Test of getCategoriesFromCourse method, of class CategoryService.
     */
    @Test
    public void testGetCategoriesFromCourse_long() throws Exception {
        Logger.getLogger(CategoryServiceTest.class.getName()).log(Level.INFO, "getCategoriesFromCourse_Long with a Long");
        List<Category> categories = categoryService.getCategoriesFromCourse(1L);
        assertNotNull(categories.size());
    }

    /**
     * Test of addCategoryToCourse method, of class CategoryService.
     */
//    @Test
//    public void testAddCategoryToCourse_Category_Course() throws Exception {
//        Logger.getLogger(CategoryServiceTest.class.getName()).log(Level.INFO, "addCategoryToCourse_Category_Course with the name of the Category as string");
//        boolean result = categoryService.addCategoryToCourse(c1, course);        
//        assertEquals(true, result);
//        categoryService.removeCategoryFromCourse(c1, course);
//    }

    /**
     * Test of addCategoryToCourse method, of class CategoryService.
     */
    @Test
    public void testAddCategoryToCourse_long_long() throws Exception {
        Logger.getLogger(CategoryServiceTest.class.getName()).log(Level.INFO, "addCategoryToCourse_Category_Course with the name of the Category as string");
        boolean result = categoryService.addCategoryToCourse(1L, 119L);        
        assertEquals(true, result);
        categoryService.removeCategoryFromCourse(1L, 119L);
    }
}
