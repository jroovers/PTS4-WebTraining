/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance.DbUtilsImpl;

import InfoSupportWeb.utility.Database;
import InfoSupportWeb.utility.LessonDAOUtils;
import InfoSupportWeb.utility.ResultSetHandlerImp;
import Model.Enrollment;
import Model.User;
import Persistance.Interfaces.IEnrollmentDAO;
import java.sql.Date;
import java.sql.SQLException;
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

    static final String QUERY_ENROLL_USER_TO_LESSON = "INSERT INTO Enrollment(ID_Lesson, ID_Student, SignupTime) VALUES (?,?, NOW());";
    static final String QUERY_GET_ENROLLMENT_BY_COURSE_ID = "SELECt e.* FROM Enrollment e, Lesson l WHERE l.ID_Lesson = e.ID_Lesson AND ID_Course = ?";
    static final String QUERY_GET_ALL_ENROLLMENTS = "SELECt e.* FROM Enrollment e";
    static final String QUERY_GET_ALL_ENROLLMENTS_BY_STATUS = "SELECt e.* FROM Enrollment e WHERE e.Status = ?";

    private static final String SQLERROR = "SQL Exception code ";
    private final static Logger LOGGER = Logger.getLogger(LessonDAOUtils.class.getName());

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
            for (Object[] o : result) {
                Calendar signupTime = new GregorianCalendar();
                signupTime.setTime(o[3] == null ? null : Date.valueOf(o[3].toString()));
                Calendar approvalTime = new GregorianCalendar();
                approvalTime.setTime(o[6] == null ? null : Date.valueOf(o[6].toString()));
                /**
                 * INSERT ENROLLMENT HERE
                 */
                //enrollments.add(e);
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return enrollments;
    }

    @Override
    public List<Enrollment> getAllEnrollmentsByCourseID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Enrollment> getAllEnrollmentsByStatus(int status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editEnrollment(Enrollment e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean approveEnrollment(Enrollment e, User manager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean rejectEnrollment(Enrollment e, User manager, String comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteEnrollment(Enrollment e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
