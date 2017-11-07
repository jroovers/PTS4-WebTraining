/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mijic
 */
public class UserServiceTest {
    
    private User user1;
    private UserService userService;
    
    @Before
    public void setUp() {
        user1 = new User("Bert", "Vissers", "Bert123", "Bertjeee112", "0612345678", "Bertje123@gmail.com", 2);
        userService = new UserService(); 
    }
    
    /**
     * Test of addUser method, of class UserService.
     */
    @Test
    public void testAddUser() throws Exception {
        System.out.println("addUser - Test");
        
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
        System.out.println("getUsers - Test");
        
        List<User> users = userService.getUsers();
        assertTrue(!users.isEmpty());
    }

    /**
     * Test of getUser method, of class UserService.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser - Test");
        
        User user = userService.getUser("Bertster");
        System.out.println(user.getName());
        assertEquals("bertus", user.getSurname());
    }

    /**
     * Test of removeUser method, of class UserService.
     */
    @Test
    public void testRemoveUser() throws Exception {
        System.out.println("removeUser - Test"); 
        
        List<User> beforeUsers = userService.getUsers();
        boolean result = userService.addUser(user1);
        List<User> users = userService.getUsers();
        assertEquals(users.size(), beforeUsers.size() + 1);
        User user = userService.getUser("Bert123");
        userService.removeUser(user.getUserID());
        List<User> afterUsers = userService.getUsers();
        assertEquals(afterUsers.size(), beforeUsers.size());
    }
    
}
