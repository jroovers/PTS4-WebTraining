/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Course;
import javax.ejb.Stateless;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
public class CourseService {

    public CourseService() {
    }

    /**
     * Persists a new course with just the bare minimals.
     * @param name
     * @param code
     * @return course with ID given by database.
     */
    public Course addCourse(String code, String name) {
        Course c = new Course(name, code);
        throw new UnsupportedOperationException("Not yet implemented.");
    }
    
        /**
     * Persists a course.
     * Persists a new course by readying all the values from a given course.
     * @param course exisiting course to copy
     * @return course with ID given by database.
     */
    public Course addCourse(Course course) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * Edits an existing course
     * @param course
     * @return 
     */
    public boolean editCourse(Course course) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * Removes an exisiting course
     * @param course
     * @return 
     */
    public boolean removeCourse(Course course) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
