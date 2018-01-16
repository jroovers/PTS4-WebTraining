package InfoSupportWeb.utility;

import java.util.List;
import Model.User;
import Persistance.Interfaces.IUserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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
    static final String QUERY_GET_USERS_WITH_PERMISSION_ID = "SELECT distinct u.* FROM User u , User_UserType ut WHERE u.ID_User = ut.ID_User AND ut.ID_UserType = ?";
    static final String QUERY_INSERT_USER = "INSERT INTO User(`Name`, `Surname`, `Username`, `Password`,`PhoneNr`,`Email`) VALUES(?,?,?,?,?,?)";
    static final String QUERY_REMOVE_USER = "DELETE FROM User WHERE ID_User = ?";
    static final String QUERY_UPDATE_USER = "UPDATE User SET Name = ?, Surname = ?, Username = ?, PhoneNr = ?, Email = ? WHERE ID_User = ?;";
    static final String QUERY_GET_ALL_USERTYPES = "SELECT Name, ID_UserType FROM UserType";
    static final String QUERY_GET_USER_USERTYPES = "SELECT ut.ID_UserType FROM User_UserType ut, User u WHERE u.ID_User = ut.ID_User AND u.ID_User = ?";
    static final String QUERY_EDIT_ACCES_LEVEL = "UPDATE User_UserType SET ID_UserType = ? WHERE ID_User = ? AND ID_UserType = ?";
    static final String QUERY_DELETE_USER_USERTYPE = "DELETE FROM User_UserType WHERE User_UserType.ID_User =?";
    static final String QUERY_ADD_USER_USERTYPE = "INSERT INTO User_UserType(`ID_User`,`ID_UserType`)VALUES(?,?)";

    //Error handling
    private final static Logger LOGGER = Logger.getLogger(UserDAOUtils.class.getName());
    private static final String SQLERROR = "SQL Exception code ";
    private static final String USERFROMDBERROR = "Failed to get users from database";

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
                        o[6] == null ? null : o[6].toString() // email
                );
                u.setAccessLevels(getUserTypesByUserID(u.getId()));
                users.add(u);
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, USERFROMDBERROR);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
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
                        o[6] == null ? null : o[6].toString() // email
                );
                user.setAccessLevels(getUserTypesByUserID(user.getId()));
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, USERFROMDBERROR);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        //`Name`, `Surname`, `Username`, `Password`,`PhoneNr`,`Email`
        Object[] params = new Object[]{user.getName(), user.getSurname(), user.getUsername(), user.getPassword(), user.getPhoneNr(), user.getEmail()};
        try {
            run.insert(QUERY_INSERT_USER, rsh, params);
            return true;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to add User in DB - errorCode: " + ex.getErrorCode(), ex);
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
            LOGGER.log(Level.SEVERE, "Failed to delete user from DB - errorCode: " + ex.getErrorCode(), ex);
            return false;
        }
    }

    @Override
    public boolean editUser(User user) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        Object[] params = new Object[]{user.getName(), user.getSurname(), user.getUsername(), user.getPhoneNr(), user.getEmail(), user.getId()};
        try {
            int inserts = run.update(QUERY_UPDATE_USER, params);
            if(inserts == 0)
                throw new SQLException("No rows edited");
            return true;            
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to edit lesson in DB - errorCode: " + ex.getMessage(), ex);
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
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
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
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, USERFROMDBERROR);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return accountTypes;
    }

    @Override
    public Set<Long> getUserTypesByUserID(long ID_user) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        Object[] params = new Object[]{ID_user};
        ArrayListHandler alh = new ArrayListHandler();
        Set<Long> usertypes = new HashSet<>();
        try {
            List<Object[]> result = run.query(QUERY_GET_USER_USERTYPES, alh, params);
            for (Object[] o : result) {
                usertypes.add(Long.parseLong(o[0].toString()));
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, "Failed to get users from DB");
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return usertypes;
    }

    @Override
    public boolean editAccountType(User user) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{user.getId()};
        try {
            run.execute(QUERY_DELETE_USER_USERTYPE, rsh, params);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to edit the AccountType in the database - errorCode: " + ex.getErrorCode(), ex);
            return false;
        }

        //add new userpermissions
        for (long level : user.getAccessLevels()) {
            run = new QueryRunner(Database.getInstance().getDataSource());
            rsh = new ResultSetHandlerImp();
            params = new Object[]{user.getId(),level};
            try {
                run.execute(QUERY_ADD_USER_USERTYPE, rsh, params);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Failed to add new userpermission - errorCode: " + ex.getErrorCode(), ex);
                return false;
            }
        }
        
        return true;
    }
}
