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

    /**
     * Removes the group based on the given name from the database.
     * 
     * @param name, name of the UserGroup
     * @return true if succes
     */
    public boolean removeUseGroup_ByName(String name);
    
    /**
     * Assigns the given usergroup too the selected course
     *
     * @param usergroup_id usergroup to add
     * @param course_id course to add usergroup to
     * @return true if succesful
     */
    public boolean addUsergroupToCourse(long usergroup_id, long course_id);

    /**
     * Unassigns the given usergroup from the given couse
     *
     * @param usergroup_id usergroup to remove
     * @param course_id course to remove usergroup from from
     * @return true if succesful
     */
    public boolean removeUsergroupFromCourse(long usergroup_id, long course_id);

    /**
     * Gets all Usergroups which are currently associated with given course
     *
     * @param course_id course to get usergroups of
     * @return List of usergroups (empty if no groups)
     */
    public List<UserGroup> getUsergroupsByCourse(long course_id);

}
