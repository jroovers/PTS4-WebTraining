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

    /**
     * Gets list of users registrated as teachers
     *
     * @return
     */
    public List<User> getTeachers() {
        UserDAOUtils userDAOUtils = new UserDAOUtils();
        return userDAOUtils.getTeachers();
    }

    /**
     * Gets a Map of accounttypes and what number they are bound too in the DB
     *
     * @return
     */
    public Map<String, String> getAccountTypes() {
        UserDAOUtils userDAOUtils = new UserDAOUtils();
        return userDAOUtils.getAllAccountTypes();
    }
    
    /**
     * Edits the AccountType of an user
     * 
     * @param user_id
     * @param acces_level
     * @param old_acces_level
     * @return Check if account type of given user is editted or not
     */
    public boolean editAccountType(User user) {
        UserDAOUtils userDAOUtils = new UserDAOUtils();
        return userDAOUtils.editAccountType(user);
    }
}
