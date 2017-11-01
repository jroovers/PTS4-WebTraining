/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pajama Sammy
 */
public class UserTest
{
    private User user;

    @Before
    public void CreateTestUser() {
        User user = new User(1, "Frank" , "franken", "Frankster", "frankisthebest", "001234", "Frankster@TheG.com", 2);
    }
   

    /**
     * Test of getUserName method, of class User.
     */
    @Test
    public void testGetUserName() {
        //arrange
        String expResult = "InfoSupportStaff1";
        //act
        String result = user.getUsername();
        //assert
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserName method, of class User.
     */
    @Test
    public void testSetUserName() {
        //arrange
        String location;
        String expLocation = "InfoSupportStaff2";
        //act
        user.setUsername(expLocation);

        location = user.getUsername();

        //assert
        assertEquals(expLocation, location);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testPassword() {
        //arrange
        String expResult = "pass";
        //act
        String result = user.getPassword();
        //assert
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        //arrange
        String code;
        String expCode = "password";
        //act
        user.setPassword(expCode);

        code = user.getPassword();

        //assert
        assertEquals(expCode, code);
    }
    /**
     * Test of add method, of class User.
     */
    @Test
    public void testAdd()
    {
        //fail("wtf does this do.");
    } 
}