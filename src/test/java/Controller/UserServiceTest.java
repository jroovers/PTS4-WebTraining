/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Mijic
 */
public class UserServiceTest {

    private User user1;
    private User user2;
    private UserService userService;

    @Before
    public void setUp() {
        user1 = new User("Bert", "Vissers", "Bert123", "Bertjeee112", "0612345678", "Bert@gmail.com", 2);
        user2 = new User(2L, "Bert", "Vissers", "Bertster", "bertisthebest", "004321", "Bert@hotmail.com", 1);
        userService = new UserService();
    }

    /**
     * Test of addUser method, of class UserService.
     */
    @Test
    public void testAddUser() throws Exception {
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "addUser - Test: ");

        boolean result = userService.addUser(user1);
        assertEquals(result, true);
        User user = userService.getUser("Bert123");
        userService.removeUser(user.getUserID());
    }

    /**
     * Test of getUsers method, of class UserService.
     */
    @Test
    public void testGetUsers() throws Exception {
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "getUsers - Test: ");

        List<User> users = userService.getUsers();
        assertTrue(!users.isEmpty());
    }

    /**
     * Test of getUser method, of class UserService.
     */
    @Test
    public void testGetUser() throws Exception {
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "getUser - Test: ");

        User user = userService.getUser("Bertster");
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, user.getName());
        assertEquals("Vissers", user.getSurname());
    }

    /**
     * Test of removeUser method, of class UserService.
     */
    @Test
    public void testRemoveUser() throws Exception {
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "removeUser - Test: ");

        List<User> beforeUsers = userService.getUsers();
        boolean result = userService.addUser(user1);
        List<User> users = userService.getUsers();
        assertEquals(users.size(), beforeUsers.size() + 1);
        User user = userService.getUser("Bert123");
        userService.removeUser(user.getUserID());
        List<User> afterUsers = userService.getUsers();
        assertEquals(afterUsers.size(), beforeUsers.size());
    }

    /**
     * Test of getTeachers method, of class UserService.
     */
    @Test
    public void testGetTeachers() throws Exception {
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "getTeachers - Test: ");

        List<User> users = userService.getTeachers();
        assertTrue(!users.isEmpty());
    }

    /**
     * Test of getAccountTypes method, of class UserService.
     */
    @Test
    public void testGetAccountTypes() throws Exception {
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "getUsers - Test: ");

        Map<String, String> accountTypes = userService.getAccountTypes();
        assertTrue(!accountTypes.isEmpty());
    }

    /**
     * Test of editUser method, of class UserService.
     */
    @Test
    public void testEditUser() throws Exception {
        Logger.getLogger(CourseServiceTest.class.getName()).log(Level.INFO, "editUser: ");
        boolean expResult = true;
        boolean result = userService.editUser(user2);
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of editAccountType method, of class UserService.
     */
    @Test
    public void testEditAcocuntType() throws Exception {
        Logger.getLogger(CourseServiceTest.class.getName()).log(Level.INFO, "editAccountType: ");
        boolean expResult = true;
        boolean result = userService.editAccountType(user2.getUserID(), 3, user2.getAccesLevel());
        assertEquals(expResult, result);
    }


}
