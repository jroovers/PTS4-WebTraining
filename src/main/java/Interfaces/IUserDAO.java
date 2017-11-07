/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.User;
import java.util.List;

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
    public List<User> getUsers();
    
    /**
     * Gets a User from database.
     *
     * @param username
     * @return User 
     */
    public User getUser(String username);
    
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
    public boolean removeUser(long User_ID);

}
