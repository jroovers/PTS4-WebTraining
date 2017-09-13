/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objects.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import Interfaces.IUserDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jeroen Roovers, Jorian, Antonio
 */
@ManagedBean(name="db")
@SessionScoped
public class UserDAOUtils implements IUserDAO {

    static final String QUERY_GET_ALL_EMPLOYEES = "SELECT u.* FROM User u WHERE UserType = 2";
    static final String QUERY_GET_OCCUPIED_EMPLOYEES = "SELECT u.* from User u JOIN UserTask ut ON u.ID_User = ut.User JOIN Task t ON ut.Task = t.ID_Task WHERE u.UserType = 2 AND t.Executed != 0 AND t.Accepted != 1";
    static final String QUERY_GET_AVAILABLE_EMPLOYEES = "SELECT u.* FROM User u WHERE UserType = 2 AND u.ID_User NOT IN (SELECT u2.ID_User from User u2 JOIN UserTask ut ON u2.ID_User = ut.User JOIN Task t ON ut.Task = t.ID_Task WHERE u2.UserType = 2 AND t.Executed != 0 AND t.Accepted != 1)";
    static final String QUERY_GET_USER = "SELECT u.* FROM User u";
    static final String QUERY_UPDATE_USERTYPE = "UPDATE User SET UserType = ? WHERE ID_User = ?";

    public UserDAOUtils() {
        
    }

    
    
//    private List<User> executeCommonQuery(String query) {
//        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
//        ArrayListHandler alh = new ArrayListHandler();
//        List<User> users = new ArrayList<>();
//        try {
//            List<Object[]> result = run.query(query, alh);
//            for (Object[] o : result) {
//                User u = new User(
//                        Integer.parseInt(o[0].toString()),
//                        o[1].toString(),
//                        o[2].toString(),
//                        o[3].toString(),
//                        o[4] == null ? null : o[4].toString(),
//                        o[5] == null ? null : o[5].toString(),
//                        o[6] == null ? null : o[6].toString(),
//                        o[7] == null ? null : o[7].toString(),
//                        o[8] == null ? null : o[8].toString(),
//                        o[9] == null ? null : o[9].toString(),
//                        o[10] == null ? null : o[10].toString()
//                );
//                users.add(u);
//            }
//        } catch (SQLException ex_sql) {
//            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
//            System.out.println(ex_sql.getMessage());
//        }
//        return users;
//    }

//    @Override
//    public List<User> getAvailableEmployees() {
//        return executeCommonQuery(QUERY_GET_AVAILABLE_EMPLOYEES);
//    }

//    @Override
//    public List<EmployeeType> getEmployeeTypes() {
//        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
//        ArrayListHandler alh = new ArrayListHandler();
//        List<EmployeeType> etypes = new ArrayList<>();
//        try {
//            List<Object[]> result = run.query(QUERY_GET_EMPLOYEE_TYPES, alh);
//            for (Object[] o : result) {
//                EmployeeType et = new EmployeeType(
//                        o[0] == null ? -1 : Integer.parseInt(o[0].toString()),
//                        o[1] == null ? null : o[1].toString()
//                );
//                etypes.add(et);
//            }
//        } catch (SQLException ex_sql) {
//            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
//            System.out.println(ex_sql.getMessage());
//        }
//        return etypes;
//    }

//    @Override
//    public void setUserType(int user_id, int user_type) {
//        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
//        try {
//            run.update(QUERY_UPDATE_USERTYPE, user_type, user_id);
//        } catch (SQLException ex_sql) {
//            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
//            System.out.println(ex_sql.getMessage());
//        }
//    }

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
