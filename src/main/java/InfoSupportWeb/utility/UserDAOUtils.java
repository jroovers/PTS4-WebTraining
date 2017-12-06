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
import java.util.LinkedHashMap;
import java.util.Map;
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
    static final String QUERY_GET_USERS_WITH_PERMISSION_ID = "SELECT Distinct User.* FROM User, User_UserType, UserType WHERE User.ID_User = User_UserType.ID_User AND User_UserType.ID_UserType = ?";
    static final String QUERY_INSERT_USER = "INSERT INTO User(`Name`, `Surname`, `Username`, `Password`,`PhoneNr`,`Email`,`ID_UserType`) VALUES(?,?,?,?,?,?,?)";
    static final String QUERY_REMOVE_USER = "DELETE FROM User WHERE ID_User = ?";
    static final String QUERY_UPDATE_USER = "UPDATE User SET Name = ?, Surname = ?, Username = ?, PhoneNr = ?, Email = ?, ID_UserType = ? WHERE ID_User = ?;";
    static final String QUERY_GET_ALL_USERTYPES = "SELECT Name, ID_UserType FROM UserType";
    static final String QUERY_GET_USER_USERTYPES = "SELECT ut.ID_UserType FROM User_UserType ut, User u WHERE u.ID_User = ut.ID_User AND u.ID_User = ?";

    public UserDAOUtils() {

    }

    @Override
    public List<User> getAllUsers() {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<User> users = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_GET_USERS, alh);
            for (Object[] o : result) {
                //(int userID ,String name, String surname, String username, String password, String phoneNr, String email, int accesLevel)
                User u = new User(
                        o[0] == null ? null : Integer.parseInt(o[0].toString()), // userID
                        o[1] == null ? null : o[1].toString(), // Name
                        o[2] == null ? null : o[2].toString(), // Surname
                        o[3] == null ? null : o[3].toString(), // username
                        o[4] == null ? null : o[4].toString(), // Password
                        o[5] == null ? null : o[5].toString(), // phoneNr
                        o[6] == null ? null : o[6].toString(), // email
                        o[7] == null ? null : Integer.parseInt(o[7].toString()) // accessLevel
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
    public User getUserByUsername(String username) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        User user = null;
        try {
            List<Object[]> result = run.query(QUERY_GET_USER, alh, username);
            for (Object[] o : result) {
                user = new User(
                        o[0] == null ? null : Integer.parseInt(o[0].toString()), // userID
                        o[1] == null ? null : o[1].toString(), // Name
                        o[2] == null ? null : o[2].toString(), // Surname
                        o[3] == null ? null : o[3].toString(), // username
                        o[4] == null ? null : o[4].toString(), // Password
                        o[5] == null ? null : o[5].toString(), // phoneNr
                        o[6] == null ? null : o[6].toString(), // email
                        o[7] == null ? null : Integer.parseInt(o[7].toString()) // accessLevel
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
        }
    }

    @Override
    public boolean removeUserByID(long User_ID) {
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

    @Override
    public boolean editUser(User user) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        Object[] params = new Object[]{user.getName(), user.getSurname(), user.getUsername(), user.getPhoneNr(), user.getEmail(), user.getAccesLevel(), user.getUserID()};
        try {
            run.update(QUERY_UPDATE_USER, params);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAOUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Failed to edit lesson in db");
            return false;
        }
    }

    public List<User> getTeachers() {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<User> users = new ArrayList<>();
        Object[] params = new Object[]{2};
        try {
            List<Object[]> result = run.query(QUERY_GET_USERS_WITH_PERMISSION_ID, alh, params);
            for (Object[] o : result) {
                User user = new User(
                        o[0] == null ? -1 : Long.parseLong(o[0].toString()),
                        o[1] == null ? null : o[1].toString(),
                        o[2] == null ? null : o[2].toString(),
                        o[4] == null ? null : o[4].toString(),
                        o[5] == null ? null : o[5].toString()
                );
                users.add(user);
            }
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
        }
        return users;
    }

    @Override
    public Map<String, String> getAllAccountTypes() {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        Map<String, String> accountTypes = new LinkedHashMap<>();
        try {
            List<Object[]> result = run.query(QUERY_GET_ALL_USERTYPES, alh);
            for (Object[] o : result) {
                accountTypes.put(o[0].toString(), o[1].toString());
            }
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.err.println("Failed to get users from database");
            System.out.println(ex_sql.getMessage());
        }
        return accountTypes;
    }

    @Override
    public List<Long> getUserTypesByUserID(long ID_user) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        Object[] params = new Object[]{ID_user};
        ArrayListHandler alh = new ArrayListHandler();
        List<Long> usertypes = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_GET_USER_USERTYPES, params, alh);
            for (Object[] o : result) {
                usertypes.add(Long.parseLong(o[0].toString()));
            }
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.err.println("Failed to get users from database");
            System.out.println(ex_sql.getMessage());
        }
        return usertypes;
    }
}
