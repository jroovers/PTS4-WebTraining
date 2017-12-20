/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Category;
import Model.Course;
import Persistance.DbUtilsImpl.CategoryDAODbUtilsImpl;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
public class CategoryService {


    public boolean addCategory(String name) {
        CategoryDAODbUtilsImpl CategoryDAO = new CategoryDAODbUtilsImpl();
        return CategoryDAO.addCategory(name);
    }

    public List<Category> getAllCategories() {
        CategoryDAODbUtilsImpl CategoryDAO = new CategoryDAODbUtilsImpl();
        return CategoryDAO.getAllCategories();
    }

    public boolean removeCategory(Category category) {
        CategoryDAODbUtilsImpl CategoryDAO = new CategoryDAODbUtilsImpl();
        return CategoryDAO.removeCategory(category);
    }
    
    public boolean removeCategory_byName(String name) {
        CategoryDAODbUtilsImpl CategoryDAO = new CategoryDAODbUtilsImpl();
        return CategoryDAO.removeCategory_byName(name);
    }

    public List<Category> getCategoriesFromCourse(Course c) {
        return getCategoriesFromCourse(c.getId());
    }

    public List<Category> getCategoriesFromCourse(long course_id) {
        CategoryDAODbUtilsImpl CategoryDAO = new CategoryDAODbUtilsImpl();
        return CategoryDAO.getCategoriesByCourse(course_id);
    }

    public boolean addCategoryToCourse(long category_id, long course_id) {
        CategoryDAODbUtilsImpl CategoryDAO = new CategoryDAODbUtilsImpl();
        return CategoryDAO.addCategoryToCourse(category_id, course_id);
    }
    
    public boolean removeCategoryFromCourse(long category_id, long course_id) {
        CategoryDAODbUtilsImpl CategoryDAO = new CategoryDAODbUtilsImpl();
        return CategoryDAO.removeCategoryFromCourse(category_id, course_id);
    }
}
