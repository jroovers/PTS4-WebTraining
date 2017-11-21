/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorian, Antonio
 */
public class User {

    private long userID;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String phoneNr;
    private String email;
    private int accesLevel;
    private List<Integer> accesLevels;

    /**
     * Constructor for pulling users from database
     *
     * @param userID
     * @param name
     * @param surname
     * @param username
     * @param password
     * @param phoneNr
     * @param email
     * @param accesLevel
     */
    public User(long userID, String name, String surname, String username, String password, String phoneNr, String email, int accesLevel) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.phoneNr = phoneNr;
        this.email = email;
        this.accesLevel = accesLevel;
    }

    /**
     * Constructor for registering a new user
     *
     * @param name
     * @param surname
     * @param username
     * @param password
     * @param phoneNr
     * @param email
     * @param accesLevel
     */
    public User(String name, String surname, String username, String password, String phoneNr, String email, int accesLevel) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.phoneNr = phoneNr;
        this.email = email;
        this.accesLevel = accesLevel;
    }

    /**
     * Constructor to register a guest
     *
     * @param name
     * @param surname
     * @param phoneNr
     * @param email
     * @param accesLevel
     */
    public User(String name, String surname, String phoneNr, String email, int accesLevel) {
        this.name = name;
        this.surname = surname;
        this.phoneNr = phoneNr;
        this.email = email;
        this.accesLevel = accesLevel;
    }

    /**
     * Constructor to get lesson registrations
     *
     * @param name
     * @param surname
     * @param phoneNr
     * @param email
     */
    public User(String name, String surname, String phoneNr, String email) {
        this.name = name;
        this.surname = surname;
        this.phoneNr = phoneNr;
        this.email = email;
    }

    /**
     * Constructor for Suppliers
     *
     * @param username
     * @param phoneNr
     * @param accesLevel
     * @param email
     */
    public User(String username, String phoneNr, String email, int accesLevel) {
        this.username = username;
        this.phoneNr = phoneNr;
        this.email = email;
        this.accesLevel = accesLevel;
    }
    
    public User(String name, String surname, String username, String phoneNr, String email, int accesLevel) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.phoneNr = phoneNr;
        this.email = email;
        this.accesLevel = accesLevel;
    }

    public List<Integer> getAccesLevels() {
        return accesLevels;
    }

    public void setAccesLevels(List<Integer> accesLevels) {
        this.accesLevels = accesLevels;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public int getAccesLevel() {
        return accesLevel;
    }

    public void setAccesLevel(int accesLevel) {
        this.accesLevel = accesLevel;
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

    public String getShortString() {
        return this.name + " " + this.surname + " - " + this.email;
    }

}
