/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance.DbUtilsImpl;

import InfoSupportWeb.utility.Database;
import InfoSupportWeb.utility.ResultSetHandlerImp;
import Interfaces.ICategoryDAO;
import Model.Category;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/**
 *
 * @author Jeroen Roovers
 */
public class CategoryDAODbUtilsImpl implements ICategoryDAO {

    static final String QUERY_INSERT_CATEGORY = "INSERT INTO Category(Name) VALUES (?)";
    static final String QUERY_SELECT_ALLCATEGORIES = "SELECT * FROM Category";
    static final String QUERY_UPDATE_CATEGORY = "UPDATE Category SET Name = ? WHERE ID_Category = ?";
    static final String QUERY_DELETE_CATEGORY = "DELETE FROM Category WHERE ID_Category = ?";

    public CategoryDAODbUtilsImpl() {
    }

    @Override
    public boolean addCategory(String name) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{name};
        try {
            run.insert(QUERY_INSERT_CATEGORY, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
            return false;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<Category> categories = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_SELECT_ALLCATEGORIES, alh);
            for (Object[] o : result) {
                Category entry = new Category(
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
    public boolean updateCategory(Category category) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{category.getName(), category.getId()};
        try {
            run.update(QUERY_UPDATE_CATEGORY, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeCategory(Category category) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{category.getId()};
        try {
            run.execute(QUERY_DELETE_CATEGORY, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
            return false;
        }
    }

}
