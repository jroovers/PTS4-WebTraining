/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import InfoSupportWeb.utility.LocationDAOUtils;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
public class LocationService {

    public LocationService() {

    }

    /**
     * Adds a new location to the system
     *
     * @param location location to add
     * @return false if any exceptions, otherwise true
     */
    public boolean addLocation(String location) {
        LocationDAOUtils locationDAOUtils = new LocationDAOUtils();
        return locationDAOUtils.addLocation(location);
    }

    /**
     * Gets all possible locations which are added to the system
     *
     * @return list of locations
     */
    public List<String> getLocations() {
        LocationDAOUtils locationDAOUtils = new LocationDAOUtils();
        return locationDAOUtils.getLocations();
    }

    /**
     * removes a given location from the system
     *
     * @param location location to remove
     * @return
     */
    public boolean removeLocation(String location) {
        throw new UnsupportedOperationException("not yet implemented");
    }

}
