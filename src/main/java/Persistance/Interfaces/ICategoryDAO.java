/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance.Interfaces;

import Model.Category;
import java.util.List;

/**
 *
 * @author Jeroen Roovers
 */
public interface ICategoryDAO {

    /**
     * Adds a new category with the given name
     *
     * @param name short description of category
     * @return true if succesful
     */
    public boolean addCategory(String name);

    /**
     * Returns a list with all categories
     *
     * @return list with categories, empty list if error or no found
     */
    public List<Category> getAllCategories();

    /**
     * Removes the given category from the database.
     *
     * @param category category with atleast id set
     * @return true if succesful
     */
    public boolean removeCategory(Category category);

    /**
     * Add the given category too the selected course
     *
     * @param category_id category to add
     * @param course_id course to add category to
     * @return true if succesful
     */
    public boolean addCategoryToCourse(long category_id, long course_id);

    /**
     * Removes the given category from the given couse
     *
     * @param category_id category to remove
     * @param course_id course to remove category from from
     * @return succesful
     */
    public boolean removeCategoryFromCourse(long category_id, long course_id);

    /**
     * Gets all categories which are currently associated with given course
     *
     * @param course_id course to get categories of
     * @return List of categories (empty if no categories)
     */
    public List<Category> getCategoriesByCourse(long course_id);
    
    /**
     * Removes a category from the DB by checking the name
     * 
     * @param name, is the name of the category
     * @return succesfull
     */
    public boolean removeCategory_byName(String name);
}
