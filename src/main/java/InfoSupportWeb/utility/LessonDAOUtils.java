/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import Persistance.Interfaces.ILessonDAO;
import Model.Course;
import Model.Lesson;
import Model.User;
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
 * @author Jorian
 */
public class LessonDAOUtils implements ILessonDAO {

    static final String QUERY_GET_LESSONS = "SELECT l.*, c.* FROM Lesson l, Course c WHERE l.ID_Course=c.ID_Course";
    static final String QUERY_INSERT_LESSON = "INSERT INTO Lesson(StartTime, EndTime, Location, ID_Course,Teacher_ID_User) VALUES(?,?,?,?,?)";
    static final String QUERY_GET_LESSONS_FROM_COURSE = "SELECT l.*, c.* FROM Lesson l, Course c WHERE l.ID_Course=c.ID_Course AND l.ID_Course = ?";
    static final String QUERY_GET_LESSONS_BY_TEACHER = "SELECT L.*, C.* FROM Lesson L, Course C WHERE L.Teacher_ID_User = ? AND L.ID_Course = C.ID_Course";
    static final String QUERY_UPDATE_LESSON = "UPDATE Lesson SET StartTime = ?, EndTime = ?, Location = ?, ID_Course = ? WHERE ID_Lesson = ?";
    static final String QUERY_REMOVE_LESSON = "DELETE FROM Lesson WHERE ID_Lesson = ?";
    static final String QUERY_SIGNUP_TEACHER_TO_LESSON = "INSERT INTO Lesson_Teacher(ID_Lesson,ID_User) VALUES (?,?);";

    static final String QUERY_ENROLL_USER_TO_LESSON = "INSERT INTO Enrollment(ID_Lesson, ID_Student, SignupTime) VALUES (?,?, NOW());";
    static final String QUERY_GET_LESSONS_BY_ENROLLED_EMAIL = "SELECT DISTINCT(u.ID_User), l.*, c.*, e.ID_Lesson from User u, Course c, Enrollment e, Lesson l WHERE u.ID_User = e.ID_Student AND e.ID_Lesson = l.ID_Lesson and c.ID_Course = l.ID_Course AND u.Email = ?";
    static final String QUERY_GET_ENROLLED_USERS_BY_LESSON = "SELECT U.Name, U.Surname, U.Email, U.PhoneNr from User U, Enrollment E WHERE E.ID_Student = U.ID_User AND E.ID_Lesson = ?;";

    //Error handling
    private static final String SQLERROR = "SQL Exception code ";
    private final static Logger LOGGER = Logger.getLogger(LessonDAOUtils.class.getName());

    @Override
    public List<Lesson> getLessons() {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<Lesson> lessons = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_GET_LESSONS, alh);
            for (Object[] o : result) {

                Calendar beginTime = new GregorianCalendar();
                beginTime.setTime(o[1] == null ? null : Date.valueOf(o[1].toString()));
                Calendar endTime = new GregorianCalendar();
                endTime.setTime(o[2] == null ? null : Date.valueOf(o[2].toString()));

                Lesson lesson = new Lesson(
                        o[0] == null ? -1 : Long.parseLong(o[0].toString()),
                        beginTime,
                        endTime,
                        o[3] == null ? null : o[3].toString(),
                        new Course(
                                o[6] == null ? -1 : Long.parseLong(o[6].toString()),
                                o[7] == null ? null : o[7].toString(),
                                o[8] == null ? null : o[8].toString(),
                                o[9] == null ? null : o[9].toString(),
                                null,
                                o[11] == null ? null : o[11].toString(),
                                o[12] == null ? null : Integer.parseInt(o[12].toString()),
                                o[13] == null ? null : Double.parseDouble(o[13].toString())
                        )
                );

                lessons.add(lesson);
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return lessons;
    }

    @Override
    public Lesson addLesson(Lesson lesson) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{lesson.getStartTime().getTime(), lesson.getEndTime().getTime(), lesson.getLocation(), lesson.getCourse().getId(), lesson.getTeacher().getId()};
        try {
            Object[] result = run.insert(QUERY_INSERT_LESSON, rsh, params);
            long id = Long.parseLong(result[0].toString());

            lesson.setId(id);

            LOGGER.log(Level.INFO, "SQL Succes, output: ", id);
            return lesson;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to add lesson to DB - errorCode: " + ex.getErrorCode(), ex);
            return null;
        }
    }

    @Override
    public boolean editLesson(Lesson lesson) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        Object[] params = new Object[]{lesson.getStartTime().getTime(), lesson.getEndTime().getTime(), lesson.getLocation(), lesson.getCourse().getId(), lesson.getId()};
        try {
            run.update(QUERY_UPDATE_LESSON, params);
            return true;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to edit lesson in DB - errorCode: " + ex.getErrorCode(), ex);
            return false;
        }
    }

    @Override
    public boolean removeLesson(long lesson_ID) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{lesson_ID};
        try {
            run.execute(QUERY_REMOVE_LESSON, rsh, params);
            return true;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to remove lesson from DB - errorCode: " + ex.getErrorCode(), ex);
            return false;
        }
    }

    @Override
    public List<Lesson> getLessonsFromCourse(long course_ID) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<Lesson> lessons = new ArrayList<>();
        Object[] params = new Object[]{course_ID};
        try {
            List<Object[]> result = run.query(QUERY_GET_LESSONS_FROM_COURSE, alh, params);
            for (Object[] o : result) {

                Calendar beginTime = new GregorianCalendar();
                beginTime.setTime(o[1] == null ? null : Date.valueOf(o[1].toString()));
                Calendar endTime = new GregorianCalendar();
                endTime.setTime(o[2] == null ? null : Date.valueOf(o[2].toString()));

                Lesson lesson = new Lesson(
                        o[0] == null ? -1 : Long.parseLong(o[0].toString()),
                        beginTime,
                        endTime,
                        o[3] == null ? null : o[3].toString(),
                        new Course(
                                o[6] == null ? -1 : Long.parseLong(o[6].toString()),
                                o[7] == null ? null : o[7].toString(),
                                o[8] == null ? null : o[8].toString(),
                                o[9] == null ? null : o[9].toString(),
                                null,
                                o[10] == null ? null : o[10].toString(),
                                o[12] == null ? null : Integer.parseInt(o[12].toString()),
                                o[13] == null ? null : Double.parseDouble(o[13].toString())
                        )
                );
                lessons.add(lesson);
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return lessons;
    }

    @Override
    public List<Lesson> GetLessonsAndRegistrationsByTeacher(long user_ID) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<Lesson> lessons = new ArrayList<>();
        Object[] params = new Object[]{user_ID};
        try {
            List<Object[]> result = run.query(QUERY_GET_LESSONS_BY_TEACHER, alh, params);
            for (Object[] o : result) {

                Calendar beginTime = new GregorianCalendar();
                beginTime.setTime(o[1] == null ? null : Date.valueOf(o[1].toString()));
                Calendar endTime = new GregorianCalendar();
                endTime.setTime(o[2] == null ? null : Date.valueOf(o[2].toString()));

                Lesson lesson = new Lesson(
                        o[0] == null ? -1 : Long.parseLong(o[0].toString()),
                        beginTime,
                        endTime,
                        o[3] == null ? null : o[3].toString(),
                        new Course(
                                o[6] == null ? -1 : Long.parseLong(o[6].toString()),
                                o[7] == null ? null : o[7].toString(),
                                o[8] == null ? null : o[8].toString(),
                                o[9] == null ? null : o[9].toString(),
                                null,
                                o[10] == null ? null : o[10].toString(),
                                o[12] == null ? null : Integer.parseInt(o[12].toString()),
                                o[13] == null ? null : Double.parseDouble(o[13].toString())
                        )
                );

                lesson.setRegistrations(GetUsersByLesson(lesson.getId()));
                lessons.add(lesson);
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return lessons;
    }

    @Override
    public List<Lesson> getLessonsByUserEmail(String email) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<Lesson> lessons = new ArrayList<>();
        Object[] params = new Object[]{email};
        try {
            List<Object[]> result = run.query(QUERY_GET_LESSONS_BY_ENROLLED_EMAIL, alh, params);
            for (Object[] o : result) {

                Calendar beginTime = new GregorianCalendar();
                beginTime.setTime(o[2] == null ? null : Date.valueOf(o[2].toString()));
                Calendar endTime = new GregorianCalendar();
                endTime.setTime(o[3] == null ? null : Date.valueOf(o[3].toString()));

                Lesson lesson = new Lesson(
                        o[1] == null ? -1 : Long.parseLong(o[1].toString()),
                        beginTime,
                        endTime,
                        o[4] == null ? null : o[4].toString(),
                        new Course(
                                o[5] == null ? -1 : Long.parseLong(o[5].toString()),
                                o[8] == null ? null : o[8].toString(),
                                o[9] == null ? null : o[9].toString(),
                                o[10] == null ? null : o[10].toString(),
                                null,
                                o[11] == null ? null : o[11].toString(),
                                o[13] == null ? null : Integer.parseInt(o[13].toString()),
                                o[14] == null ? null : Double.parseDouble(o[14].toString())
                        )
                );

                lesson.setRegistrations(GetUsersByLesson(lesson.getId()));
                lessons.add(lesson);
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return lessons;
    }

    private List<User> GetUsersByLesson(long lesson_id) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<User> users = new ArrayList<>();
        Object[] params = new Object[]{lesson_id};
        try {
            List<Object[]> result = run.query(QUERY_GET_ENROLLED_USERS_BY_LESSON, alh, params);
            for (Object[] o : result) {

                String name = o[0].toString();
                String surname = o[1].toString();
                String email = o[2].toString();
                String phone = o[3].toString();

                User u = new User(name, surname, phone, email);

                users.add(u);
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return users;
    }

    @Override
    public long signUpUser(long lesson_ID, long user_ID) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{lesson_ID, user_ID};
        try {
            run.insert(QUERY_ENROLL_USER_TO_LESSON, rsh, params);

            LOGGER.log(Level.INFO, "SQL Succes");
            return 1;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to sign up User in DB - errorCode: " + ex.getErrorCode(), ex);
            return 0;
        }
    }

}
