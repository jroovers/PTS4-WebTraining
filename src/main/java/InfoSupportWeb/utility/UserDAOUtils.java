/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import Interfaces.IUserDAO;

/**
 *
 * @author Jeroen Roovers, Jorian, Antonio
 */
public class UserDAOUtils implements IUserDAO {


    static final String QUERY_GET_USER = "SELECT u.* FROM User u";

    public UserDAOUtils() {
        
    }

    @Override
    public List<User> getUsers() {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<User> users = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_GET_USER, alh);
            for (Object[] o : result) {
                User user = new User();
                user.setUsername(o[1] == null ? null : o[1].toString());
                user.setPassword(o[2] == null ? null : o[2].toString());
//                User user = new User(
//                        o[0] == null ? -1 : Integer.parseInt(o[0].toString()),
//                        o[1] == null ? null : o[1].toString(),
//                        o[2] == null ? null : o[2].toString()
//                );
                users.add(user);
            }
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
        }
        return users;
    }
    
    public String test(){
        return "test";
    }
}
