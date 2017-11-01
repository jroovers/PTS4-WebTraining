/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;


import InfoSupportWeb.utility.AuthorizationUtils;
import InfoSupportWeb.utility.UserDAOUtils;
import Model.User;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author kyle_
 */
@Named(value = "authorizationBean")
@RequestScoped
public class AuthorizationBean {

    private UserDAOUtils userdb;
    private User user;

    private String username;
    private String role;

    

    public AuthorizationBean() {
        username = "";
        role = "werknemer";
    }
    
    
    

    public String login() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        if (username.isEmpty()) {
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Geen gebruikersnaam ingevoerd.", "!"));
//        } else {
//            try {
//                user = userdb.getUser(username);
//            } catch (NullPointerException ex) {
//                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "De gebruikersnaam bestaat niet.", "!"));
//                return;
//            }
//            HttpSession hs = AuthorizationUtils.getSession();
//            hs.setAttribute("Username", username);
//            hs.setAttribute("Role", role);
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingelogd", "!"));
//        }

            User user = new User(1, "Frank" , "franken", "Frankster", "frankisthebest", "001234", "Frankster@TheG.com", 2);
            HttpSession hs = AuthorizationUtils.getSession();
            hs.setAttribute("LoggedInUser", user);
            System.out.println("Sessie gestart van: " + user.getUsername());
            return "/index_templated.xhtml";
    }
    
    public String logout() {
        HttpSession hs = AuthorizationUtils.getSession();
        hs.invalidate();
        return "/authorization.xhtml";
    }
    
    // 1 admin
    // 2 docent
    // 3 cursist
    public void checkPermission(int accesLevel) throws IOException {
        HttpSession hs = AuthorizationUtils.getSession();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if(hs.getAttribute("LoggedInUser") != null) {
            user = (User) hs.getAttribute("LoggedInUser");
            
            
            if(user.getAccesLevel() > accesLevel) {
               context.redirect("/InfoSupportWeb/external/authorization.xhtml"); 
            }    
        } else {
            context.redirect("/InfoSupportWeb/external/authorization.xhtml"); 
        }
    }
}
