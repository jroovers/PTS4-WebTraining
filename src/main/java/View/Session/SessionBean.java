/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Session;

import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author Jereoen Roovers
 */
@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    boolean loggedIn;

    boolean employee;
    boolean teacher;
    boolean manager;
    boolean admin;

    /**
     * Creates a new instance of sessionBean
     */
    public SessionBean() {
    }

    private void CheckPermission(boolean value_to_check) {
        if (this.loggedIn) {
            if (value_to_check) {
                return;
            }
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect("/InfoSupportWeb/external/authorization.xhtml");
        } catch (IOException ex) {
            System.out.println("IOEXCEPTION@AuthorizationBean.CheckPermissionNew:");
            System.out.println(ex.getMessage());
        }
    }

    public void CheckIfManager(ComponentSystemEvent event) throws AbortProcessingException {
        CheckPermission(this.manager);
    }

    public void CheckIfAdmin(ComponentSystemEvent event) throws AbortProcessingException {
        CheckPermission(this.admin);
    }

    public void CheckIfTeacher(ComponentSystemEvent event) throws AbortProcessingException {
        CheckPermission(this.admin || this.teacher);
    }

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
