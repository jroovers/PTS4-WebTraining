/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance.Interfaces;

import Model.UserGroup;
import java.util.List;

/**
 *
 * @author Jeroen Roovers
 */
public interface IUserGroupDAO {

    /**
     * Adds a new usergroup with the given name
     *
     * @param name short description of group
     * @return true if success
     */
    public boolean addUserGroup(String name);

    /**
     * updates a existing usergroup
     *
     * @param group group with id AND name set
     * @return true if success
     */
    public boolean updateUserGroup(UserGroup group);

    /**
     * Returns a list with all usergroups
     *
     * @return list with usergroups, empty list if error or no found
     */
    public List<UserGroup> getAllUserGroups();

    /**
     * Removes the given group from the database.
     *
     * @param group group with atleast id set
     * @return true if success
     */
    public boolean removeUseGroup(UserGroup group);

}
