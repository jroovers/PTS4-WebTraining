/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import Persistance.Interfaces.ICourseDAO;
import Model.Course;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/**
 *
 * @author Jorian
 */
public class CourseDAOUtils implements ICourseDAO {

    static final String QUERY_GET_COURSES = "SELECT * FROM Course";
    static final String QUERY_INSERT_COURSE = "INSERT INTO Course"
            + "(Code, Name, Description, CourseMaterials, KeyWords, Duration, Cost, Supplier) VALUES"
            + "(?,?,?,?,?,?,?,?)";
    static final String QUERY_UPDATE_COURSE = "UPDATE Course SET Code = ?, Name = ?, Description = ?, CourseMaterials = ?, KeyWords = ?, Duration = ?, Cost = ?, Supplier = ? WHERE ID_Course = ?";
    static final String QUERY_REMOVE_COURSE = "DELETE FROM Course WHERE ID_Course = ?";

    //Error handling
    private static final String SQLERROR = "SQL Exception code ";
    private final static Logger LOGGER = Logger.getLogger(CourseDAOUtils.class.getName());

    @Override
    public List<Course> getCourses() {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<Course> courses = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_GET_COURSES, alh);
            for (Object[] o : result) {
                Course course = new Course(
                        o[0] == null ? -1 : Long.parseLong(o[0].toString()),
                        o[1] == null ? null : o[1].toString(),
                        o[2] == null ? null : o[2].toString(),
                        o[3] == null ? null : o[3].toString(),
                        null,
                        o[4] == null ? null : o[4].toString(),
                        o[6] == null ? null : Integer.parseInt(o[6].toString()),
                        o[7] == null ? null : Double.parseDouble(o[7].toString())
                );

                String[] keyWords = o[5] == null ? null : o[5].toString().split(",");
                course.setKeyWords(keyWords);
                course.setSupplier(o[8] == null ? null : o[8].toString());
                courses.add(course);
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return courses;
    }

    @Override
    public Course addCourse(Course course) {

        String keyWords;
        if (course.getKeyWords() != null) {
            keyWords = arrayToString(course.getKeyWords());
        } else {
            keyWords = "";
        }
        try {
            QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
            ResultSetHandlerImp rsh = new ResultSetHandlerImp();
            Object[] params = new Object[]{course.getCode(), course.getName(), course.getDescription(), course.getCourseMaterials(), keyWords, course.getDurationInDays(), course.getCost(), course.getSupplier()};

            Object[] result = run.insert(QUERY_INSERT_COURSE, rsh, params);

            long id = Long.parseLong(result[0].toString());

            course.setId(id);
            LOGGER.log(Level.FINE, "SQL Succes, output: {0}", id);
            return course;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex.getErrorCode(), ex);
            LOGGER.log(Level.SEVERE, "Failed to add course to DB", ex);
            return null;
        }
    }

    @Override
    public boolean editCourse(Course course) {

        String keyWords = arrayToString(course.getKeyWords());

        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        Object[] params = new Object[]{course.getCode(), course.getName(), course.getDescription(), course.getCourseMaterials(), keyWords, course.getDurationInDays(), course.getCost(), course.getSupplier(), course.getId()};
        try {
            run.update(QUERY_UPDATE_COURSE, params);
            return true;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex.getErrorCode(), ex);
            LOGGER.log(Level.SEVERE, "Failed to edit course in DB", ex);
            return false;
        }
    }

    @Override
    public boolean removeCourse(long course_ID) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{course_ID};
        try {
            run.execute(QUERY_REMOVE_COURSE, rsh, params);
            return true;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex.getErrorCode(), ex);
            LOGGER.log(Level.SEVERE, "Failed to remove course from DB", ex);
            return false;
        }
    }

    private String arrayToString(String[] array) {
        if (array == null) {
            return "";
        }
        StringBuilder keyWords = new StringBuilder();
        for (String s : array) {
            keyWords.append(s);
            keyWords.append(",");
        }
        return keyWords.toString();
    }
}
