/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance.DbUtilsImpl;

import InfoSupportWeb.utility.Database;
import InfoSupportWeb.utility.LessonDAOUtils;
import InfoSupportWeb.utility.ResultSetHandlerImp;
import Model.Course;
import Model.Enrollment;
import Model.Lesson;
import Model.User;
import Persistance.Interfaces.IEnrollmentDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/**
 *
 * @author Jeroen Roovers
 */
public class EnrollmentDAOUtilsImpl implements IEnrollmentDAO {

    /**
     * Do not update these 3 queries without also modifying listFillerHelper to
     * use the new fields.
     */
    static final String QUERY_LONGSELECT = "SELECt e.*, u1.name, u1.Surname, u2.name, u2.surname, c.Name FROM Enrollment e LEFT JOIN User u1 ON e.ID_Student = u1.ID_User LEFT JOIN User u2 ON e.ID_Manager = u2.ID_User JOIN Lesson l ON e.ID_Lesson = l.ID_Lesson JOIN Course c ON l.ID_Course = c.ID_Course";
    static final String QUERY_GET_ENROLLMENT_BY_COURSE_ID = QUERY_LONGSELECT + " AND c.ID_Course = ?";
    static final String QUERY_GET_ALL_ENROLLMENTS = QUERY_LONGSELECT;
    static final String QUERY_GET_ALL_ENROLLMENTS_BY_STATUS = QUERY_LONGSELECT + " AND e.Status = ?";
    static final String QUERY_GET_ENROLLMENT_BY_USERNAME = "SELECT c.Name, l.StartTime, l.Location, e.Status, e.Comment FROM Enrollment e, User u, Lesson l , Course c  WHERE e.ID_Student = u.ID_User   AND e.ID_Lesson = l.ID_Lesson  AND l.ID_Course = c.ID_Course  AND e.ID_Student = ?";

    static final String QUERY_ENROLL_USER_TO_LESSON = "INSERT INTO Enrollment(ID_Lesson, ID_Student, SignupTime) VALUES (?,?, NOW());";
    static final String QUERY_DELETE_ENROLLMENT = "DELETE FROM Enrollment WHERE ID_Enrollment = ?";
    static final String QUERY_APPROVE_ENROLLMENT = "UPDATE Enrollment SET Status = 1, ID_Manager = ? WHERE ID_Enrollment = ?";
    static final String QUERY_REJECT_ENROLLMENT = "UPDATE Enrollment SET Status = 2, ID_Manager = ?, COMMENT = ? WHERE ID_Enrollment = ?";
    private static final String SQLERROR = "SQL Exception code ";
    private final static Logger LOGGER = Logger.getLogger(LessonDAOUtils.class.getName());

    /**
     * Helper method for reading multiple lines of Enrollment. Only use in
     * methods that select enrollment*.
     *
     * @param result query results
     * @param enrollments list to fill
     * @return list of enrollments
     */
    private List<Enrollment> listFillerHelper(List<Object[]> result, List<Enrollment> enrollments) {
        for (Object[] o : result) {
            // Check and set possible NULL fields.
            Calendar signupTime = new GregorianCalendar();
            signupTime.setTime(o[3] == null ? null : (Timestamp) o[3]);
            Calendar approvalTime = new GregorianCalendar();
            if (o[6] == null) {
                approvalTime = null;
            } else {
                approvalTime.setTime((Timestamp) o[6]);
            }
            Lesson lesson = new Lesson();
            lesson.setId(Integer.valueOf(o[1].toString()));
            Course course = new Course();
            course.setName(o[12].toString());
            lesson.setCourse(course);

            User student, manager;
            student = new User();
            student.setId(Integer.valueOf(o[1].toString()));
            student.setName(o[8].toString());
            student.setSurname(o[9].toString());
            if (o[5] == null) {
                manager = null;
            } else {
                manager = new User();
                manager.setId(Integer.valueOf(o[5].toString()));
                manager.setName(o[10].toString());
                manager.setSurname(o[11].toString());
            }
            String comment;
            if (o[7] == null) {
                comment = null;
            } else {
                comment = o[7].toString();
            }

            // Create model, add to list.
            Enrollment e = new Enrollment();
            e.setId(Long.valueOf(o[0].toString()));
            e.setStudent(student);
            e.setLesson(lesson);
            e.setSignupTime(signupTime);
            e.setStatus(Integer.valueOf(o[4].toString()));
            e.setManager(manager);
            e.setApprovalTime(approvalTime);
            e.setComment(comment);

            enrollments.add(e);
        }
        return enrollments;
    }

    @Override
    public Enrollment addEnrollment(Enrollment e) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{e.getLesson().getId(), e.getStudent().getId()};
        try {
            Object[] result = run.insert(QUERY_ENROLL_USER_TO_LESSON, rsh, params);
            long id = Long.parseLong(result[0].toString());
            e.setId(id);
            LOGGER.log(Level.INFO, "SQL Succes");
            return e;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to enroll into lesson in DB - errorCode: " + ex.getErrorCode(), ex);
            return null;
        }
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<Enrollment> enrollments = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_GET_ALL_ENROLLMENTS, alh);
            listFillerHelper(result, enrollments);
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return enrollments;
    }

    @Override
    public List<Enrollment> getAllEnrollmentsByCourseID(long id) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<Enrollment> enrollments = new ArrayList<>();
        Object[] params = new Object[]{id};
        try {
            List<Object[]> result = run.query(QUERY_GET_ENROLLMENT_BY_COURSE_ID, alh, params);
            listFillerHelper(result, enrollments);
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return enrollments;
    }

    @Override
    public List<Enrollment> getAllEnrollmentsByStatus(int status) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<Enrollment> enrollments = new ArrayList<>();
        Object[] params = new Object[]{status};
        try {
            List<Object[]> result = run.query(QUERY_GET_ALL_ENROLLMENTS_BY_STATUS, alh, params);
            listFillerHelper(result, enrollments);
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return enrollments;
    }

    public List<Enrollment> GetEnrollmentsByUser(int userID) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<Enrollment> enrollments = new ArrayList<>();
        Object[] params = new Object[]{userID};
        try {
            List<Object[]> result = run.query(QUERY_GET_ENROLLMENT_BY_USERNAME, alh, params);
            //List<Object[]> result = run.query(QUERY_GET_ENROLLMENTS, alh);

            for (Object[] o : result) {

                Enrollment enrollment = new Enrollment();
                Object a = o[3];
                enrollment.setStatus(Integer.parseInt(a.toString()));
                Lesson l = new Lesson();

                Calendar beginTime = new GregorianCalendar();
                beginTime.setTime(o[1] == null ? null : Date.valueOf(o[1].toString()));

                l.setStartTime(beginTime);
                Course c = new Course();
                c.setName(o[0].toString());
                l.setCourse(c);
                l.setLocation(o[2].toString());
                enrollment.setLesson(l);

                User u = new User();

                u.setEmail(o[3].toString());
                enrollment.setComment(o[4] == null ? null : o[4].toString());
                enrollments.add(enrollment);
            }
        } catch (Exception ex_sql) {
            System.out.println(ex_sql);
        }
        return enrollments;
    }

    @Override
    public boolean editEnrollment(Enrollment e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean approveEnrollment(Enrollment e, User manager) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        Object[] params = new Object[]{manager.getId(), e.getId()};
        boolean result = false;
        try {
            int updatedRows = run.update(QUERY_APPROVE_ENROLLMENT, params);
            if (updatedRows == 1) {
                result = true;
            }
            LOGGER.log(Level.INFO, "SQL Succes");
            return result;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to approve enrollment in DB - errorCode: " + ex.getErrorCode(), ex);
            return false;
        }
    }

    @Override
    public boolean rejectEnrollment(Enrollment e, User manager, String comment) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        Object[] params = new Object[]{manager.getId(), comment, e.getId()};
        boolean result = false;
        try {
            int updatedRows = run.update(QUERY_REJECT_ENROLLMENT, params);
            if (updatedRows == 1) {
                result = true;
            }
            LOGGER.log(Level.INFO, "SQL Succes");
            return result;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to reject enrollment in DB - errorCode: " + ex.getErrorCode(), ex);
            return false;
        }
    }

    @Override
    public boolean deleteEnrollment(Enrollment e) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        Object[] params = new Object[]{e.getId()};
        boolean result = false;
        try {
            int updatedRows = run.update(QUERY_DELETE_ENROLLMENT, params);
            if (updatedRows == 1) {
                result = true;
            }
            LOGGER.log(Level.INFO, "SQL Succes");
            return result;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to delete enrollment from DB - errorCode: " + ex.getErrorCode(), ex);
            return false;
        }
    }
}
