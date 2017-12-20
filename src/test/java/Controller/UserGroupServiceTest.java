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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addUserGroup method, of class UserGroupService.
     */
    @Test
    public void testAddUserGroup() throws Exception {
        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "addUserGroup with the name of the UserGroup as a String");
        UserGroup ug3 = new UserGroup("Test");
        List<UserGroup> userGroups = us.getAllUserGroups();
        boolean result = us.addUserGroup("Test Usergroups");
        List<UserGroup> expUserGroups = us.getAllUserGroups();
        assertEquals(true, result);
        assertEquals(userGroups.size() + 1, expUserGroups.size());
        us.removeUserGroup_ByName("Test Usergroups");
        us.removeUserGroup(ug3);
    }

    /**
     * Test of updateUserGroup method, of class UserGroupService.
     */
//    @Test
//    public void testUpdateUserGroup() throws Exception {
//        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "updateUserGroup with the name of the UserGroup as a String");
//        boolean result = us.updateUserGroup(ug1);
//        assertEquals(true, result);
//        us.updateUserGroup(ug2);
//    }

    /**
     * Test of getUserGroupsFromCourse method, of class UserGroupService.
     */
    @Test
    public void testGetUserGroupsFromCourse_Course() throws Exception {
        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "getUserGroupsFromCourse_Course test");
        List<UserGroup> userGroups = us.getUserGroupsFromCourse(course);
        assertNotNull(userGroups.size());
    }

    /**
     * Test of getUserGroupsFromCourse method, of class UserGroupService.
     */
    @Test
    public void testGetUserGroupsFromCourse_long() throws Exception {
        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "getUserGroupsFromCourse_long test");
        List<UserGroup> userGroups = us.getUserGroupsFromCourse(119L);
        assertNotNull(userGroups.size());
    }

    /**
     * Test of addUserGroupToCourse method, of class UserGroupService.
     */
//    @Test
//    public void testAddUserGroupToCourse_UserGroup_Course() throws Exception {
//        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "addUserGroupToCourse_UserGroup_Course test");
//        boolean result = us.addUserGroupToCourse(ug2, course);
//        assertEquals(true, result);
//        us.removeUserGroupFromCourse(ug2, course);
//    }

    /**
     * Test of addUserGroupToCourse method, of class UserGroupService.
     */
    @Test
    public void testAddUserGroupToCourse_long_long() throws Exception {
        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "addUserGroupToCourse_long_long test");
        boolean result = us.addUserGroupToCourse(1L, 119L);
        assertEquals(true, result);
        us.removeUserGroupFromCourse(1L, 119L);
    }

    /**
     * Test of removeUserGroupFromCourse method, of class UserGroupService.
     */
//    @Test
//    public void testRemoveUserGroupFromCourse_UserGroup_Course() throws Exception {
//        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "removeUserGroupFromCourse_UserGroup_Course test");
//        boolean result = us.addUserGroupToCourse(ug2, course);
//        assertEquals(true, result);
//        us.removeUserGroupFromCourse(ug2, course);
//    }

    /**
     * Test of removeUserGroupFromCourse method, of class UserGroupService.
     */
    @Test
    public void testRemoveUserGroupFromCourse_long_long() throws Exception {
        Logger.getLogger(UserGroupServiceTest.class.getName()).log(Level.INFO, "removeUserGroupFromCourse_long_long with the courseID and userGroupID as long");
        boolean result = us.addUserGroupToCourse(1L, 119L);
        assertEquals(true, result);
        us.removeUserGroupFromCourse(1L, 119L);
    }

}
