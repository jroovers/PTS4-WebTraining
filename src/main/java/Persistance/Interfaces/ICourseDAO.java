/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance.Interfaces;

import Model.Course;
import java.util.List;

/**
 *
 * @author Jorian
 */
public interface ICourseDAO {
    
    /**
     * Gets a list of courses from database.
     *
     * @return Course List
     */
    public List<Course> getCourses();
    
    /**
     * Adds course to database.
     *
     * @return Return course with database ID
     */
    public Course addCourse(Course course);
    
    /**
     * Edits course in database.
     *
     * @return Success or failure
     */
    public boolean editCourse(Course course);
    
    /**
     * Removes course in database.
     *
     * @return Success or failure
     */
    public boolean removeCourse(long course_ID);
}
