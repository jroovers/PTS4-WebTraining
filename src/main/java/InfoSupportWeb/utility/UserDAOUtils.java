/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import static InfoSupportWeb.utility.LocationDAOUtils.QUERY_GET_LOCATIONS;
import java.util.List;
import Model.User;
import Interfaces.IUserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/**
 *
 * @author Jeroen Roovers, Jorian, Antonio
 */
public class UserDAOUtils implements IUserDAO {


    static final String QUERY_GET_USERS = "SELECT * FROM User";

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
                //(long userID ,String name, String surname, String username, String password, String phoneNr, String email, int accesLevel)
                User u = new User(
                    o[1] == null ? null : Long.parseLong(o[1].toString()), // userID
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
            System.out.println(ex_sql.getMessage());
        }
        return users;
    }

    @Override
    public User getUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUser(String userName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

 
