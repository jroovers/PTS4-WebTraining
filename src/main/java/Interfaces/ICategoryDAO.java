/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

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
     * @return true if success
     */
    public boolean addCategory(String name);

    /**
     * updates a existing category
     *
     * @param category category with id AND name set
     * @return true if success
     */
    public boolean updateCategory(Category category);

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
     * @return true if success
     */
    public boolean removeCategory(Category category);

}
