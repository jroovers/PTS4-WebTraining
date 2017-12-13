/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.User;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jeroen Roovers, Jorian, Antonio
 */
public interface IUserDAO {

    /**
     * Gets a list of users from database.
     *
     * @return User List
     */
    public List<User> getAllUsers();

    /**
     * Gets a User from database.
     *
     * @param username
     * @return User
     */
    public User getUserByUsername(String username);

    /**
     * Adds a User to database.
     *
     * @param user
     * @return
     */
    public boolean addUser(User user);

    /**
     * Removes a User from database.
     *
     * @param User_ID
     * @return
     */
    public boolean removeUserByID(long User_ID);

    /**
     * Edits an existing User.
     *
     * @param user
     * @return
     */
    public boolean editUser(User user);

    /**
     * Returns a map with string description and id's of all possible usertypes.
     *
     * @return
     */
    public Map<String, String> getAllAccountTypes();

    /**
     * Returns a list with all the accounttype ID's that are assigned to a
     * registered user.
     *
     * @param ID_user user to check accounttypes of
     * @return list of usertype ID's.
     */
    public Set<Long> getUserTypesByUserID(long ID_user);

    /**
     * gets list of teachers from database
     *
     * @return list of teachers from database
     */
    public List<User> getTeachers();
    
    /**
     * Edits an existing AccountType of an existing User
     *    
     * @param user_id
     * @param acces_level
     * @param old_acces_level
     * @return Check if accountType is editted or not
     */
    public boolean editAccountType(User user);
}
