/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    private User failUser;
    private UserService userService;
    private String longString;

    @Before
    public void setUp() {
        user1 = new User(1L,"Bert", "Vissers", "Bert123", "Bertjeee112", "0612345678", "Bertje123@gmail.com");
        user2 = new User(2L, "Bert", "Vissers", "Bertster", "bertisthebest", "004321", "Bert@hotmail.com");

        userService = new UserService();
        
        //Unhappy flow purposes
        longString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        failUser = new User(999999999999999999L,longString, "Vissers", "Bertster", "bertisthebest", "004321", "Bert@hotmail.com");
    }

    /**
     * Test of addUser method, of class UserService.
     */
    @Test
    public void testAddUser() throws Exception {
        //Setup
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "addUser - Test: ");
        boolean result = userService.addUser(user1);
        
        //Assert
        assertEquals(result, true);
        
        //Cleanup DB
        User user = userService.getUser("Bert123");
        userService.removeUser(user.getId());
        
        //Unhappy flow testing
        boolean failResult = userService.addUser(failUser);
        assertEquals(failResult, false);
    }

    /**
     * Test of getUsers method, of class UserService.
     */
    @Test
    public void testGetUsers() throws Exception {
        //Setup
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "getUsers - Test: ");
        List<User> users = userService.getUsers();
        
        //Assert
        assertTrue(!users.isEmpty());
    }

    /**
     * Test of getUser method, of class UserService.
     */
    @Test
    public void testGetUser() throws Exception {
        //Setup
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "getUser - Test: ");
        User user = userService.getUser("Bertster");
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, user.getName());
        
        //Assert
        assertEquals("Vissers", user.getSurname());
    }

    /**
     * Test of removeUser method, of class UserService.
     */
    @Test
    public void testRemoveUser() throws Exception {
        //Setup
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "removeUser - Test: ");
        List<User> beforeUsers = userService.getUsers();
        boolean result = userService.addUser(user1);
        List<User> users = userService.getUsers();
        User user = userService.getUser("Bert123");
        userService.removeUser(user.getId());
        List<User> afterUsers = userService.getUsers();
        
        //Asserts
        assertEquals(users.size(), beforeUsers.size() + 1);
        assertEquals(afterUsers.size(), beforeUsers.size());      
    }

    /**
     * Test of getTeachers method, of class UserService.
     */
    @Test
    public void testGetTeachers() throws Exception {
        //Setup
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "getTeachers - Test: ");
        List<User> users = userService.getTeachers();
        //Assert
        assertTrue(!users.isEmpty());
    }

    /**
     * Test of getAccountTypes method, of class UserService.
     */
    @Test
    public void testGetAccountTypes() throws Exception {
        //Setup
        Logger.getLogger(LessonServiceTest.class.getName()).log(Level.INFO, "getUsers - Test: ");
        Map<String, String> accountTypes = userService.getAccountTypes();
        
        //Assert
        assertTrue(!accountTypes.isEmpty());
    }

    /**
     * Test of editUser method, of class UserService.
     */
    @Test
    public void testEditUser() throws Exception {
        //Setup
        Logger.getLogger(CourseServiceTest.class.getName()).log(Level.INFO, "editUser: ");
        boolean expResult = true;
        boolean result = userService.editUser(user2);
        
        //Assert
        assertEquals(expResult, result);
        
         //Test unhappy flow
        boolean failResult = userService.editUser(failUser);
        assertEquals(failResult, false);
    }

    /**
     * Test of editAccountType method, of class UserService.
     */
    @Test
    public void testEditAcocuntType() throws Exception {
        //Setup
        Logger.getLogger(CourseServiceTest.class.getName()).log(Level.INFO, "editAccountType: ");
        boolean expResult = true;
        boolean result = userService.editAccountType(user2);
        
        //Assert
        assertEquals(expResult, result);
        
        //Test unhappy flow
        Set<Long> failAccesLevels = new HashSet<>();
        failAccesLevels.add(999999999999999999L);
        failAccesLevels.add(999999999999999998L);
        failUser.setAccessLevels(failAccesLevels);
        boolean failResult = userService.editAccountType(failUser);
        assertEquals(failResult, false);
    }

}
