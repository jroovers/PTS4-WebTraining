/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import InfoSupportWeb.utility.CourseDAOUtils;
import Model.Course;
import java.util.List;
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
     *
     * @param name
     * @param code
     * @return course with ID given by database.
     */
    public Course addCourse(String code, String name) {
        CourseDAOUtils courseDAOUtils = new CourseDAOUtils();
        
        Course c = new Course(code, name);
        return courseDAOUtils.addCourse(c);
    }

    /**
     * Persists a course. Persists a new course by readying all the values from
     * a given course.
     *
     * @param course exisiting course to copy
     * @return course with ID given by database.
     */
    public Course addCourse(Course course) {
        CourseDAOUtils courseDAOUtils = new CourseDAOUtils();
        return courseDAOUtils.addCourse(course);
    }

    /**
     * Gets all courses from the database,
     *
     * @return list of courses
     */
    public List<Course> getAllCourses() {
        CourseDAOUtils courseDAOUtils = new CourseDAOUtils();
        List<Course> courses = courseDAOUtils.getCourses();
        return courses;
    }
    
    /**
     * Gets a single course from the database bsaed on give the code
     * @param code
     * @return course if found, otherwise null
     */
    public Course getCourseByCode(String code){
        return null;
    }

    /**
     * Edits an existing course
     *
     * @param course
     * @return
     */
    public boolean editCourse(Course course) {
        CourseDAOUtils courseDAOUtils = new CourseDAOUtils();
        return courseDAOUtils.editCourse(course);
    }

    /**
     * Removes an exisiting course
     *
     * @param course
     * @return
     */
    public boolean removeCourse(long course_ID) {
        CourseDAOUtils courseDAOUtils = new CourseDAOUtils();
        return courseDAOUtils.removeCourse(course_ID);
    }
}
