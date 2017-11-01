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
public class UserTest {

    private User user;

    @Before
    public void CreateTestUser() {
        user = new User(1, "Frank", "franken", "Frankster", "frankisthebest", "001234", "Frankster@TheG.com", 2);
    }

    /**
     * Test of getUserID method, of class User.
     */
    @Test
    public void testGetUserID() {
        //arange
        long expID = 1;
        //act
        long result = user.getUserID();
        //assert
        assertEquals(expID, result);
    }

    /**
     * Test of setUserID method, of class User.
     */
    @Test
    public void testSetUserID() {
        //arange
        long newID = 2;
        //act
        user.setUserID(newID);
        long result = user.getUserID();
        //assert
        assertEquals(newID, result);
    }

    /**
     * Test of getAccesLVL method, of class User.
     */
    @Test
    public void testGetAccesLVL() {
        //arange
        int expLVL = 2;
        //act
        int result = user.getAccesLevel();
        //assert
        assertEquals(expLVL, result);
    }

    /**
     * Test of setAccesLVL method, of class User.
     */
    @Test
    public void testSetAccesLVL() {
        //arange
        int newLVL = 1;
        //act
        user.setAccesLevel(newLVL);
        int result = user.getAccesLevel();
        //assert
        assertEquals(newLVL, result);
    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        //arange
        String expName = "Frank";
        //act
        String result = user.getName();
        //assert
        assertEquals(expName, result);
    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetName() {
        //arange
        String newName = "Henk";
        //act
        user.setName(newName);
        String result = user.getName();
        //assert
        assertEquals(newName, result);
    }

    /**
     * Test of getSurname method, of class User.
     */
    @Test
    public void testGetSurname() {
        //arange
        String expSurname = "franken";
        //act
        user.setSurname(expSurname);
        String result = user.getSurname();
        //assert
        assertEquals(expSurname, result);
    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetSurname() {
        //arange
        String newSurname = "Henken";
        //act
        user.setSurname(newSurname);
        String result = user.getSurname();
        //assert
        assertEquals(newSurname, result);
    }

    /**
     * Test of getUserName method, of class User.
     */
    @Test
    public void testGetUserName() {
        //arrange
        String expResult = "Frankster";
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
        String expResult = "frankisthebest";
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
     * Test of setPassword method, of class User.
     */
    @Test
    public void testGetPhoneNR() {
        //arrange
        String expPhoneNR = "001234";
        //act        
        String result = user.getPhoneNr();
        //assert
        assertEquals(expPhoneNR, result);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPhoneNR() {
        //arrange
        String newPhoneNr = "123400";
        //act
        user.setPhoneNr(newPhoneNr);
        String result = user.getPhoneNr();
        //assert
        assertEquals(newPhoneNr, result);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testGetEmail() {
        //arrange
        String expEmail = "Frankster@TheG.com";
        //act
        String result = user.getEmail();
        //assert
        assertEquals(expEmail, result);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetEmail() {
        //arrange
        String newEmail = "Frankster@TheG.com";
        //act
        user.setEmail(newEmail);
        String result = user.getEmail();
        //assert
        assertEquals(newEmail, result);
    }
}
