/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.UserService;
import InfoSupportWeb.utility.AuthorizationUtils;
import InfoSupportWeb.utility.UserDAOUtils;
import Model.User;
import View.Session.SessionBean;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author kyle_
 * @author Jeroen Roovers
 */
@Named(value = "authorizationBean")
@RequestScoped
public class AuthorizationBean {

    @Inject
    private SessionBean session;
    @Inject
    private UserService service;

    private UserDAOUtils userdb;
    private User user;

    private String username;
    private String password;

    public AuthorizationBean() {
        username = "";
    }

    /**
     * Logs in the user with all permissions.
     *
     * @return String for redirection using XHTML action
     */
    public String quickLogin() {
        session.setAllBools(true);
        session.setUser(service.getUser("Frankster"));
        return "/index_templated.xhtml";
    }

    /**
     * Logs out the user by invalidating the user saved in the session and
     * redirecting to the main page.
     *
     * @return String for redirection using XHTML action
     */
    public String quickLogout() {
        session.logOff();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index_templated.xhtml?faces-redirect=true";
    }

    /**
     * Dirty check if user is BUM, should be refactored in something more
     * modular and extensible (what if customer wants to add extra roles??)
     */
    public void checkIfManager() {
        checkPermissionNew(session.isManager());
    }

    /**
     * Dirty check if user is admin, should be refactored in something more
     * modular and extensible (what if customer wants to add extra roles??)
     */
    public void checkIfAdmin() {
        checkPermissionNew(session.isAdmin());
    }

    /**
     * Dirty check if user is a Teacher, should be refactored in something more
     * modular and extensible (what if customer wants to add extra roles??)
     */
    public void checkIfTeacher() {
        checkPermissionNew((session.isTeacher() || session.isAdmin()));
    }

    /**
     * Helper method for quick n dirty permissionchecks
     *
     * @param value_to_check
     */
    private void checkPermissionNew(boolean value_to_check) {
        if (session.isLoggedIn()) {
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

    /**
     * Login method, needs a input into username and password of this class.
     * Checks these against the exisiting accounts in the database, and sets the
     * session user if succesful or redirect to the login form if false without
     * granting elevated rights.
     *
     * @return string for redirection using XHTML action
     */
    public String login() {
        if (username != null && password != null) {
            user = service.getUser(username);
            if (user.getPassword().equals(password)) {
                session.setUser(user);
                return "/index_templated.xhtml";
            }
        }

        // Gebruiker    : Bertster
        // Wachtwoord   : bertisthebest
        // Level        : 1
        // Gebruiker    : Frankster
        // Wachtwoord   : frankisthebest
        // Level        : 2
        return "/authorization.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSession(SessionBean session) {
        this.session = session;
    }
}
