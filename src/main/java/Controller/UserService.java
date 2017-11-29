/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import InfoSupportWeb.utility.CourseDAOUtils;
import InfoSupportWeb.utility.UserDAOUtils;
import Model.Course;
import Model.User;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author Jorian
 */
@Stateless
public class UserService {
    
    public UserService() {
    }

    /**
     * Persists a new User.
     *
     * @param user
     * @return 
     */
    public boolean addUser(User user) {
        UserDAOUtils userDAOUtils = new UserDAOUtils();
        return userDAOUtils.addUser(user);
    }

    /**
     * Persists a User.
     *
     * @return all users.
     */
    public List<User> getUsers() {
        UserDAOUtils userDAOUtils = new UserDAOUtils();
        return userDAOUtils.getAllUsers();
    }

    /**
     *
     * @param username
     * @return selected user.
     */
    public User getUser(String username) {
        UserDAOUtils userDAOUtils = new UserDAOUtils();
        return userDAOUtils.getUserByUsername(username);
    }

    /**
     * Removes an existing user
     *
     * @param user_ID
     * @return
     */
    public boolean removeUser(long user_ID) {
        UserDAOUtils userDAOUtils = new UserDAOUtils();
        return userDAOUtils.removeUserByID(user_ID);
    }
    
    /**
     * Edits an existing user
     * 
     * @param user
     * @return 
     */
    public boolean editUser(User user) {
        UserDAOUtils userDAOUtils = new UserDAOUtils();
        return userDAOUtils.editUser(user);
    }
    
    public Map<String, String> getAccountTypes() {
        UserDAOUtils userDAOUtils = new UserDAOUtils();
        return userDAOUtils.getAllAccountTypes();
    }
}
