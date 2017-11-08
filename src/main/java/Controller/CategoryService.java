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

    public CategoryService() {
    }

    public boolean addCategory(String name) {
        CategoryDAODbUtilsImpl CategoryDAO = new CategoryDAODbUtilsImpl();
        return CategoryDAO.addCategory(name);
    }

    public boolean updateCategory(Category category) {
        CategoryDAODbUtilsImpl CategoryDAO = new CategoryDAODbUtilsImpl();
        return CategoryDAO.updateCategory(category);
    }

    public List<Category> getAllCategories() {
        CategoryDAODbUtilsImpl CategoryDAO = new CategoryDAODbUtilsImpl();
        return CategoryDAO.getAllCategories();
    }

    public boolean removeCategory(Category category) {
        CategoryDAODbUtilsImpl CategoryDAO = new CategoryDAODbUtilsImpl();
        return CategoryDAO.removeCategory(category);
    }

    public boolean addCategoryToCourse(Category category, Course course) {
        return false;
    }

    public boolean addCategoryToCourse(long category_id, long course_id) {
        return false;
    }

    public boolean removeCategoryFromCourse(Category category, Course course) {
        return false;
    }

    public boolean removeCategoryFromCourse(long category_id, long course_id) {
        return false;
    }
}
