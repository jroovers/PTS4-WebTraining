/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance.Interfaces;

import java.util.List;

/**
 *
 * @author Jorian
 */
public interface ILocationDAO {
    /**
     * Gets a list of locations from database.
     *
     * @return String List with locations
     */
    public List<String> getLocations();
    
    /**
     * Adds a location to the database.
     *
     * @return True if succeeded, false when failed to insert into database.
     */
    public boolean addLocation(String locationName);
    
    /**
     * Removes a location from the database.
     *
     * @return True if succeeded, false when failed to remove location from database.
     */
    public boolean removeLocation(String locationName);
}
