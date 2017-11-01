/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import java.util.List;
import Model.User;
import Interfaces.IUserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/**
 *
 * @author Jeroen Roovers, Jorian, Antonio
 */
public class UserDAOUtils implements IUserDAO {

    static final String QUERY_GET_USER = "SELECT * FROM User where Username = ?";
    static final String QUERY_GET_USERS = "SELECT * FROM User";
    static final String QUERY_INSERT_USER = "INSERT INTO User(`Name`, `Surname`, `Username`, `Password`,`PhoneNr`,`Email`,`ID_UserType`) VALUES(?,?,?,?,?,?,?)";
    static final String QUERY_REMOVE_USER = "DELETE FROM User WHERE ID_User = ?";


    public UserDAOUtils() {

    }

    @Override
    public List<User> getUsers() {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<User> users = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_GET_USERS, alh);
            for (Object[] o : result) {
                //(int userID ,String name, String surname, String username, String password, String phoneNr, String email, int accesLevel)
                User u = new User(
                        o[1] == null ? null : Integer.parseInt(o[1].toString()), // userID
                        o[2] == null ? null : o[2].toString(), // Name
                        o[3] == null ? null : o[3].toString(), // Surname
                        o[4] == null ? null : o[4].toString(), // username
                        o[5] == null ? null : o[5].toString(), // Password
                        o[6] == null ? null : o[6].toString(), // phoneNr
                        o[7] == null ? null : o[7].toString(), // email
                        o[8] == null ? null : Integer.parseInt(o[8].toString()) // accessLevel
                );
                users.add(u);
            }
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.err.println("Failed to get users from database");
            System.out.println(ex_sql.getMessage());
        }
        return users;
    }

    @Override
    public User getUser(String username) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        User user = null;
        try {
            List<Object[]> result = run.query(QUERY_GET_USER, alh, username);
            for (Object[] o : result) {
                user = new User(
                        o[1] == null ? null : Integer.parseInt(o[1].toString()), // userID
                        o[2] == null ? null : o[2].toString(), // Name
                        o[3] == null ? null : o[3].toString(), // Surname
                        o[4] == null ? null : o[4].toString(), // username
                        o[5] == null ? null : o[5].toString(), // Password
                        o[6] == null ? null : o[6].toString(), // phoneNr
                        o[7] == null ? null : o[7].toString(), // email
                        o[8] == null ? null : Integer.parseInt(o[8].toString()) // accessLevel
                );
            }
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.err.println("Failed to get user from database");
            System.out.println(ex_sql.getMessage());
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        //`Name`, `Surname`, `Username`, `Password`,`PhoneNr`,`Email`,`ID_UserType`
        Object[] params = new Object[]{user.getName(), user.getSurname(), user.getUsername(), user.getPassword(), user.getPhoneNr(), user.getEmail(), user.getAccesLevel()};
        try {
            run.insert(QUERY_INSERT_USER, rsh, params);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAOUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Failed to add User to db");
            return false;
        }}

    @Override
    public boolean removeUser(long User_ID) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{User_ID};
        try {
            run.execute(QUERY_REMOVE_USER, rsh, params);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAOUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Failed to remove user from db");
            return false;
        }
    }

}
