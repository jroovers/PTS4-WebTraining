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
 * @author Jeroen
 */
public class UserGroupTest {

    UserGroup instanceDefault;
    UserGroup instanceName;
    UserGroup instanceIdName;
    String expectedName;
    long expectedId;

    public UserGroupTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        expectedName = "Testgroep";
        expectedId = 110L;

        instanceDefault = new UserGroup();
        instanceName = new UserGroup(expectedName);
        instanceIdName = new UserGroup(expectedId, expectedName);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class UserGroup.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        // primitive long defaults to 0
        long expResult1 = 0L;
        long result1 = instanceDefault.getId();
        assertEquals(expResult1, result1);
        long expResult2 = 0L;
        long result2 = instanceName.getId();
        assertEquals(expResult2, result2);
        // happy flow on a instance which has its id set
        long expResult3 = expectedId;
        long result3 = instanceIdName.getId();
        assertEquals(expResult3, result3);
    }

    /**
     * Test of setId method, of class UserGroup.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        // happy flow
        long id = 50L;
        UserGroup instance = new UserGroup();
        instance.setId(id);
        assertEquals(id, instance.getId());
        // long primitive should be unsigned
        long negativeId = -50L;
        long positiveId = 50L;
        UserGroup instance2 = new UserGroup();
        instance.setId(id);
        assertEquals(positiveId, instance.getId());
    }

    /**
     * Test of getName method, of class UserGroup.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        // String defaults to null
        UserGroup instance1 = new UserGroup();
        String expResult1 = null;
        String result1 = instance1.getName();
        assertEquals(expResult1, result1);
        // happy flow        
        UserGroup instance2 = new UserGroup("hello");
        String expResult2 = "hello";
        String result2 = instance2.getName();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of setName method, of class UserGroup.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        UserGroup instance = new UserGroup();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

}
