/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance.DbUtilsImpl;

import InfoSupportWeb.utility.Database;
import InfoSupportWeb.utility.ResultSetHandlerImp;
import Model.UserGroup;
import static Persistance.DbUtilsImpl.CategoryDAODbUtilsImpl.QUERY_INSERT_CATEGORY;
import Persistance.Interfaces.IUserGroupDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/**
 *
 * @author Jeroen Roovers
 */
public class UserGroupDAODbUtilsImpl implements IUserGroupDAO {

    static final String QUERY_INSERT_USERGROUP = "INSERT INTO UserGroup(Name) VALUES (?)";
    static final String QUERY_SELECT_ALLUSERGROUPS = "SELECT * FROM UserGroup ORDER BY Name";
    static final String QUERY_UPDATE_USERGROUP = "UPDATE UserGroup SET Name = ? WHERE ID_UserGroup = ?";
    static final String QUERY_DELETE_USERGROUP = "DELETE FROM UserGroup WHERE ID_UserGroup = ?";

    static final String QUERY_INSERT_COURSEUSERGROUP = "INSERT INTO Course_UserGroup(ID_Course, ID_UserGroup) VALUES (?, ?)";
    static final String QUERY_DELETE_COURSEUSERGROUP = "DELETE FROM Course_UserGroup WHERE ID_Course = ? AND ID_UserGroup = ?";
    static final String QUERY_SELECT_USERGROUPSBYCOURSE = "SELECT usg.* FROM UserGroup usg, Course_UserGroup cu WHERE usg.ID_UserGroup = cu.ID_UserGroup AND cu.ID_Course = ?";

    @Override
    public boolean addUserGroup(String name) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{name};
        try {
            run.insert(QUERY_INSERT_USERGROUP, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUserGroup(UserGroup group) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{group.getName(), group.getId()};
        try {
            run.update(QUERY_UPDATE_USERGROUP, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
            return false;
        }
    }

    @Override
    public List<UserGroup> getAllUserGroups() {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<UserGroup> categories = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_SELECT_ALLUSERGROUPS, alh);
            for (Object[] o : result) {
                UserGroup entry = new UserGroup(
                        o[0] == null ? -1 : Long.parseLong(o[0].toString()),
                        o[1] == null ? null : o[1].toString()
                );
                categories.add(entry);
            }
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
        }
        return categories;
    }

    @Override
    public boolean removeUseGroup(UserGroup group) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{group.getId()};
        try {
            run.execute(QUERY_DELETE_USERGROUP, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
            return false;
        }
    }

    @Override
    public boolean addUsergroupToCourse(long usergroup_id, long course_id) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{course_id, usergroup_id};
        try {
            run.insert(QUERY_INSERT_COURSEUSERGROUP, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeUsergroupFromCourse(long usergroup_id, long course_id) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{course_id, usergroup_id};
        try {
            run.execute(QUERY_DELETE_COURSEUSERGROUP, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
            return false;
        }
    }

    @Override
    public List<UserGroup> getUsergroupsByCourse(long course_id) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        Object[] params = new Object[]{course_id};
        List<UserGroup> usergroups = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_SELECT_USERGROUPSBYCOURSE, alh, params);
            for (Object[] o : result) {
                UserGroup entry = new UserGroup(
                        o[0] == null ? -1 : Long.parseLong(o[0].toString()),
                        o[1] == null ? null : o[1].toString()
                );
                usergroups.add(entry);
            }
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
        }
        return usergroups;
    }

}
