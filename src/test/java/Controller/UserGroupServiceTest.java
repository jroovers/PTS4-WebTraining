/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Course;
import Model.UserGroup;
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
public class UserGroupServiceTest {

    private UserGroupService us = new UserGroupService();
    private UserGroup ug1;
    private UserGroup ug2;
    private Course course;
    private Course failCourse;
    private String longString;

    public UserGroupServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ug1 = new UserGroup(1, "MDWK Instroom 2017 - Test Change");
        ug2 = new UserGroup(1, "MDWK Instroom 2017");

        //course
        String[] testArray = new String[2];
        String testSupplier = "InfoSupport";
        testArray[0] = "testcase1";
        testArray[1] = "testcase2";
        course = new Course(119, "UnitTest1", "name", "textDescription", testArray, "courseMaterials", testArray, 3, 1.1);

        //Unhappy flow testing purposes
        longString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        failCourse = new Course(999999999999999999L, "UnitTest1", "name", "textDescription", testArray, "courseMaterials", testArray, 3, 1.1);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addUserGroup method, of class UserGroupService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddUserGroup() throws Exception {
        //Setup
        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "addUserGroup with the name of the UserGroup as a String");
        UserGroup ug3 = new UserGroup("Test");
        List<UserGroup> userGroups = us.getAllUserGroups();
        boolean result = us.addUserGroup("Test Usergroups");
        List<UserGroup> expUserGroups = us.getAllUserGroups();

        //Asserts
        assertEquals(true, result);
        assertEquals(userGroups.size() + 1, expUserGroups.size());

        //DB Clean
        us.removeUserGroup_ByName("Test Usergroups");
        us.removeUserGroup(ug3);

        //Testing unhappy flow
        boolean badResult = us.addUserGroup(longString);
        assertEquals(false, badResult);
    }

    /**
     * Test of getUserGroupsFromCourse method, of class UserGroupService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUserGroupsFromCourse_Course() throws Exception {
        //Setup
        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "getUserGroupsFromCourse_Course test");
        List<UserGroup> userGroups = us.getUserGroupsFromCourse(course);

        //Assert
        assertNotNull(userGroups.size());
    }

    /**
     * Test of getUserGroupsFromCourse method, of class UserGroupService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUserGroupsFromCourse_long() throws Exception {
        //Setup
        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "getUserGroupsFromCourse_long test");
        
        //Assert
        List<UserGroup> userGroups = us.getUserGroupsFromCourse(1L);
        assertNotNull(userGroups.size());
    }

    /**
     * Test of addUserGroupToCourse method, of class UserGroupService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddUserGroupToCourse_long_long() throws Exception {
        //Setup
        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "addUserGroupToCourse_long_long test");
        boolean result = us.addUserGroupToCourse(1L, 1L);

        //Assert
        assertEquals(true, result);

        //Cleanup DB
        us.removeUserGroupFromCourse(1L, 1L);

        //Testing unhappy flow
        boolean failResult = us.addUserGroupToCourse(failCourse.getId(), failCourse.getId());
        assertEquals(false, failResult);
    }

    /**
     * Test of removeUserGroupFromCourse method, of class UserGroupService.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveUserGroupFromCourse_long_long() throws Exception {
        //Setup
        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "removeUserGroupFromCourse_long_long with the courseID and userGroupID as long");
        boolean result = us.addUserGroupToCourse(1L, 1L);

        //Assert
        assertEquals(true, result);

        //Cleanup DB 
        us.removeUserGroupFromCourse(1L, 1L);
    }

}