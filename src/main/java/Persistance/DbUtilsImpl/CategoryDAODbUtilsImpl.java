/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance.DbUtilsImpl;

import InfoSupportWeb.utility.Database;
import InfoSupportWeb.utility.ResultSetHandlerImp;
import Persistance.Interfaces.ICategoryDAO;
import Model.Category;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/**
 *
 * @author Jeroen Roovers
 */
public class CategoryDAODbUtilsImpl implements ICategoryDAO {

    static final String QUERY_INSERT_CATEGORY = "INSERT INTO Category(Name) VALUES (?)";
    static final String QUERY_SELECT_ALLCATEGORIES = "SELECT * FROM Category ORDER BY Name";
    static final String QUERY_UPDATE_CATEGORY = "UPDATE Category SET Name = ? WHERE ID_Category = ?";
    static final String QUERY_DELETE_CATEGORY = "DELETE FROM Category WHERE ID_Category = ?";
    static final String QUERY_DELETE_CATEGORY_BY_NAME = "DELETE FROM Category WHERE Name = ?";
    
    static final String QUERY_INSERT_COURSECATEGORY = "INSERT INTO Course_Category(ID_Course, ID_Category) VALUES (?, ?)";
    static final String QUERY_DELETE_COURSECATEGORY = "DELETE FROM Course_Category WHERE ID_Course = ? AND ID_Category = ?";
    static final String QUERY_SELECT_CATEGORIESBYCOURSE = "SELECT cat.* FROM Category cat, Course_Category cc WHERE cat.ID_Category = cc.ID_Category AND cc.ID_Course = ? ORDER BY cat.Name";

    //Error handling
    private final static Logger LOGGER = Logger.getLogger(CategoryDAODbUtilsImpl.class.getName());
    private static final String SQLERROR = "SQL Exception code ";

    @Override
    public boolean addCategory(String name) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{name};
        try {
            run.insert(QUERY_INSERT_CATEGORY, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
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
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return categories;
    }

    @Override
    public boolean updateCategory(Category category) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());        
        Object[] params = new Object[]{category.getName(), category.getId()};
        try {
            run.update(QUERY_UPDATE_CATEGORY, params);
            return true;
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
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
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
            return false;
        }
    }
    
    @Override
    public boolean removeCategory_byName(String name) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{name};
        try {
            run.execute(QUERY_DELETE_CATEGORY_BY_NAME, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
            return false;
        }
    }

    @Override
    public boolean addCategoryToCourse(long category_id, long course_id) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{course_id, category_id};
        try {
            run.insert(QUERY_INSERT_COURSECATEGORY, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
            return false;
        }
    }

    @Override
    public boolean removeCategoryFromCourse(long category_id, long course_id) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{course_id, category_id};
        try {
            run.execute(QUERY_DELETE_COURSECATEGORY, rsh, params);
            return true;
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
            return false;
        }
    }

    @Override
    public List<Category> getCategoriesByCourse(long course_id) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        Object[] params = new Object[]{course_id};
        List<Category> categories = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_SELECT_CATEGORIESBYCOURSE, alh, params);
            for (Object[] o : result) {
                Category entry = new Category(
                        o[0] == null ? -1 : Long.parseLong(o[0].toString()),
                        o[1] == null ? null : o[1].toString()
                );
                categories.add(entry);
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return categories;
    }

}
