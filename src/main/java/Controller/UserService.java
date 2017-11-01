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
        return userDAOUtils.getUsers();
    }

    /**
     *
     * @param username
     * @return selected user.
     */
    public User getUser(String username) {
        UserDAOUtils userDAOUtils = new UserDAOUtils();
        return userDAOUtils.getUser(username);
    }

    /**
     * Removes an existing user
     *
     * @param user_ID
     * @return
     */
    public boolean removeUser(long user_ID) {
        UserDAOUtils userDAOUtils = new UserDAOUtils();
        return userDAOUtils.removeUser(user_ID);
    }
}
