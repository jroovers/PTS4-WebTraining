/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.UserService;
import Model.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Antonio
 */
@Named(value = "manageAccountBean")
@RequestScoped
public class ManageAccountBean {

    @Inject
    UserService us;

    private List<User> allUsers;
    private Map<String, String> accountTypes;
    private int accountType;
    private User selectedUser;
    private String userID;
    private String name;
    private String surname;
    private String email;
    private String phonenr;
    private String username;

    /**
     * Creates a new instance of manageAccountBean
     */
    public ManageAccountBean() {
        accountTypes = new LinkedHashMap<String, String>();
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenr() {
        return phonenr;
    }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
    }

    public List<User> getAllUsers() {
        allUsers = us.getUsers();

        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null) {
            this.username = username;
        } else {
            this.username = "Geen gebruikersnaam geregistreerd";
        }
    }

    public Map<String, String> getAccountTypes() {
        //TODO:
        accountTypes = us.getAccountTypes();
        return accountTypes;
    }

    public void setAccountTypes(Map<String, String> accountTypes) {
        this.accountTypes = accountTypes;
    }

    public String valueChanged(ValueChangeEvent e) {
        long longid = Long.parseLong(e.getNewValue().toString());
        for (User u : allUsers) {
            if (u.getUserID() == longid) {
                setName(u.getName());
                setSurname(u.getSurname());
                setUsername(u.getUsername());
                setPhonenr(u.getPhoneNr());
                setEmail(u.getEmail());
                setAccountType(u.getAccesLevel());
                break;
            }
        }
        return "manageaccounts";
    }

    public String editUser() {
        User user = new User(userID, name, surname, username, phonenr, email, accountType);
        boolean succes = us.editUser(user);
        FacesContext context = FacesContext.getCurrentInstance();
        if (succes) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gebruiker aangepast", "!"));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Gebruiker niet aangepast, probeer later opnieuw", "!"));
        }
        return "manageaccounts";
    }

    public String deleteUser() {
        boolean succes = us.removeUser(Long.parseLong(userID));
        FacesContext context = FacesContext.getCurrentInstance();
        if (succes) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gebruiker verwijderd", "!"));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Gebruiker niet verwijderd, probeer later opnieuw", "!"));
        }
        return "manageaccounts";
    }
    
    public void changeListener(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ManageAccountBean manageAccountBean = (ManageAccountBean) facesContext.getApplication().createValueBinding("#{manageAccountBean}").getValue(facesContext);
        manageAccountBean.setUserID(manageAccountBean.getUserID());
        long longid = Long.parseLong(manageAccountBean.getUserID());
        for (User u : allUsers) {
            if (u.getUserID() == longid) {
                setName(u.getName());
                setSurname(u.getSurname());
                setUsername(u.getUsername());
                setPhonenr(u.getPhoneNr());
                setEmail(u.getEmail());
                setAccountType(u.getAccesLevel());
                break;
            }
        }
    }
}
