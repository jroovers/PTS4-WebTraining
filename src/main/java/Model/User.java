package Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jorian, Antonio
 */
@ManagedBean(name = "obj")
@SessionScoped
public class User {

    private int userID;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String phoneNr;
    private String email;

    public User() {

    }

    public User(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }
    
    public User(int userID ,String username, String password, int accesLevel){
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public User(String name, String surname, String phoneNr, String email) {
        this.name = name;
        this.surname = surname;
        this.phoneNr = phoneNr;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public String add() {
        System.out.println(username + " " + username);

        if (!"1".equals(username)) {
            return "failurePage";
        } else {
            return "test";
        }
    }

}
