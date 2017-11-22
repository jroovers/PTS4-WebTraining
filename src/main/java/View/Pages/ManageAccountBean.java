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
    private Long userID;
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
        accountTypes.put("Admin", "1");
        accountTypes.put("User", "2");
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
        editUser();
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        selectedUser.setName(name);
        us.editUser(selectedUser); 
        //editUser();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        editUser();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        editUser();
    }

    public String getPhonenr() {
        return phonenr;
    }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
        editUser();
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
        this.username = username;
        editUser();
    }

    public Map<String, String> getAccountTypes() {
        //TODO:
        //accountTpes = us.getAccountTypes();
        return accountTypes;
    }

    public void setAccountTypes(Map<String, String> accountTypes) {
        this.accountTypes = accountTypes;
    }

    public void valueChanged(ValueChangeEvent e) {
        userID = (Long) e.getNewValue();
        for (User u : allUsers) {
            if(u.getUserID() == userID) {
                setName(u.getName());
                setSurname(u.getSurname());
                setUsername(u.getUsername());
                setPhonenr(u.getPhoneNr());
                setEmail(u.getEmail());
                setAccountType(u.getAccesLevel());
            }
        }
    }

    public void editUser() {
        User user = new User(userID, name, surname, username, phonenr, email, accountType);
        //TODO:
        us.editUser(user); 
    }

}
