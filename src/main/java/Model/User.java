package Model;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorian, Antonio, Ricardo
 */
public class User {

    private long userID;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String phoneNr;
    private String email;
    private Set<Long> accessLevels = new HashSet<>();

    //dit is echt heel lelijk maarja moet wel zo xo
    private boolean hasAccess1;
    private boolean hasAccess2;
    private boolean hasAccess3;
    private boolean hasAccess4;

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
    public User(long userID, String name, String surname, String username, String password, String phoneNr, String email) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.phoneNr = phoneNr;
        this.email = email;
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
    public User(String name, String surname, String username, String password, String phoneNr, String email) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.phoneNr = phoneNr;
        this.email = email;
    }

    /**
     * Constructor to register a guest and get lesson registrations
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
     * Constructor to get lesson teachers
     *
     * @param name
     * @param surname
     * @param phoneNr
     * @param email
     */
    public User(long id, String name, String surname, String phoneNr, String email) {
        this.userID = id;
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
    public User(String username, String phoneNr, String email) {
        this.username = username;
        this.phoneNr = phoneNr;
        this.email = email;
    }

    /**
     * Constructor for Manage Users
     *
     * @param userID
     * @param name
     * @param surname
     * @param username
     * @param phoneNr
     * @param email
     * @param accesLevel
     */
    public User(Long userID, String name, String surname, String username, String phoneNr, String email) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.phoneNr = phoneNr;
        this.email = email;
    }

    public boolean getHasAccess1() {
        return hasAccess1;
    }

    public void setHasAccess1(boolean hasAccess1) {
        this.hasAccess1 = hasAccess1;
        setAccesLevel(1, hasAccess1);
    }

    public boolean getHasAccess2() {
        return hasAccess2;
    }

    public void setHasAccess2(boolean hasAccess2) {
        this.hasAccess2 = hasAccess2;
        setAccesLevel(2, hasAccess2);
    }

    public boolean getHasAccess3() {
        return hasAccess3;
    }

    public void setHasAccess3(boolean hasAccess3) {
        this.hasAccess3 = hasAccess3;
        setAccesLevel(3, hasAccess3);
    }

    public boolean getHasAccess4() {
        return hasAccess4;
    }

    public void setHasAccess4(boolean hasAccess4) {
        this.hasAccess4 = hasAccess4;
        setAccesLevel(4, hasAccess4);
    }

    private void setAccesLevel(long i, boolean hasAccess) {
        if (hasAccess) {
            accessLevels.add(i);
        } else {
            accessLevels.remove(i);
        }
    }

    public Set<Long> getAccesLevels() {
        return accessLevels;
    }

    public void setAccesLevels(Set<Long> accessLevels) {
        this.accessLevels = accessLevels;
        if (this.accessLevels.contains((long) 1)) {
            hasAccess1 = true;
        }
        if (this.accessLevels.contains((long) 2)) {
            hasAccess2 = true;
        }
        if (this.accessLevels.contains((long) 3)) {
            hasAccess3 = true;
        }
        if (this.accessLevels.contains((long) 4)) {
            hasAccess4 = true;
        }
    }

    public void addAccessLevel(long accessLevel) {
        this.accessLevels.add(accessLevel);
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
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
        Logger.getLogger(User.class.getName()).log(Level.INFO, username + " " + username);

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
