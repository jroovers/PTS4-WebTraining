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
import java.util.ArrayList;
import java.util.List;
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
    private String password;
    private String role;
    
    private List course_page = new ArrayList<>() ;
    private List panner_page = new ArrayList();
    private List personalroster_page = new ArrayList();
    private List schedule_page = new ArrayList();
    private List offertraining_page = new ArrayList();
    private List signup_page = new ArrayList();

    public AuthorizationBean() {
        username = "";
        role = "werknemer";
    }
    
     public String logindummie() {
         List<Integer> acceslist = new ArrayList<Integer>();
         acceslist.add(1);
         acceslist.add(2);
         HttpSession hs = AuthorizationUtils.getSession();
         hs.setAttribute("UserID", 1);
         hs.setAttribute("AccesLevels", acceslist);
         hs.setAttribute("Username", "Kyle");
         return "/index_templated.xhtml";
         
     }
    

    public String login() {
        
        if(username != null && password != null) {
            userdb = new UserDAOUtils();
            user = userdb.getUser(username);
            
            if(user.getPassword().equals(password)) {
                HttpSession hs = AuthorizationUtils.getSession();
                hs.setAttribute("UserID", user.getUserID());
                hs.setAttribute("AccesLevels", user.getAccesLevels());
                hs.setAttribute("Username", user.getUsername());
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
    
    public String logout() {
        HttpSession hs = AuthorizationUtils.getSession();
        hs.invalidate();
        return "/index_templated.xhtml";
    }
    
    
//    /**
//     * Checks if the user has the permission to use the page.
//     * @param accesLevel Level that the user has acces to.
//     * @throws IOException 
//     */
//    public void checkPermission(int accesLevel) throws IOException {
//        HttpSession hs = AuthorizationUtils.getSession();
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        try {
//            int level = (int) hs.getAttribute("AccesLevel");
//
//            if (accesLevel == 1) {
//                return;
//            }
//
//            if (level >= accesLevel) {
//                return;
//            }
//            context.redirect("/InfoSupportWeb/external/authorization.xhtml");
//        } catch (NullPointerException ex) {
//            context.redirect("/InfoSupportWeb/external/authorization.xhtml");
//        }
//    }
    
    
    public void checkPermission(int accesLevel) throws IOException {
        HttpSession hs = AuthorizationUtils.getSession();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        try {
            List levelList = (List) hs.getAttribute("AccesLevels");
            int level;

            for (Object obj : levelList) {
                level = (int) obj;
                if (level == accesLevel) {
                    return;
                }
            }
            
            context.redirect("/InfoSupportWeb/external/authorization.xhtml"); 
        } catch(Exception ex) {
            context.redirect("/InfoSupportWeb/external/authorization.xhtml"); 
        }
 
    }
    
       /**
     * Checks if the users acceslevel is high enough.
     * @param accesLevel
     * @return True if acceslevel is high enough, false if not
     */
    public boolean checkVisible(int accesLevel) {
        HttpSession hs = AuthorizationUtils.getSession();
        try {
            List levelList = (List) hs.getAttribute("AccesLevels");
            int level;

            for (Object obj : levelList) {
                level = (int) obj;
                if (level == accesLevel) {
                    return true;
                }
            }
            return false;  
        } catch (NullPointerException ex) {
            return false;
        }
    }

//    
//        /**
//     * Checks if the users acceslevel is high enough.
//     * @param accesLevel
//     * @return True if acceslevel is high enough, false if not
//     */
//    public boolean checkVisible(int accesLevel) {
//        HttpSession hs = AuthorizationUtils.getSession();
//        try {
//            int level = (int) hs.getAttribute("AccesLevel");      
//            
//            if (accesLevel == 1) {
//                return true;
//            }
//            
//            if (level >= accesLevel) {
//                return true;
//            }
//            return false;  
//        } catch (NullPointerException ex) {
//            return false;
//        }
//    }

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
    
    
}
