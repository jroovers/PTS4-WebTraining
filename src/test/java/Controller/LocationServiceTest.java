/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
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
public class LocationServiceTest {
    
    LocationService ls = new LocationService();
    String l1 = "Venray";
    
    public LocationServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addLocation method, of class LocationService.
     */
    @Test
    public void testAddLocation() throws Exception {
        Logger.getLogger(LocationServiceTest.class.getName()).log(Level.INFO, "addLocation with the name of the Location as a String");
        List<String> locations = ls.getLocations();
        boolean result = ls.addLocation(l1);
        List<String> expLocations = ls.getLocations();
        assertEquals(true, result);
        assertEquals(locations.size() + 1, expLocations.size());
        ls.removeLocation(l1);        
    }    
}
