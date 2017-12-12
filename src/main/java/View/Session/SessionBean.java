/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Session;

import Model.User;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Jereoen Roovers
 */
@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    private User user;
    /**
     * Dirty solution for acces management; Ideally these booleans are
     * incorperated into the User object and loaded whenever an user is loaded.
     */
    private boolean loggedIn;
    private boolean employee;
    private boolean teacher;
    private boolean manager;
    private boolean admin;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public boolean isTeacher() {
        return teacher;
    }

    public void setTeacher(boolean teacher) {
        this.teacher = teacher;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAllBools(boolean status) {
        this.loggedIn = status;
        this.employee = status;
        this.teacher = status;
        this.manager = status;
        this.admin = status;
    }

    public void logOff() {
        setAllBools(false);
    }

}
