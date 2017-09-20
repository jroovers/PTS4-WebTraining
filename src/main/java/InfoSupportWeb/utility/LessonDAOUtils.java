/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import static InfoSupportWeb.utility.CourseDAOUtils.QUERY_INSERT_COURSE;
import Interfaces.ILessonDAO;
import Model.Course;
import Model.Lesson;
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
    static final String QUERY_INSERT_LESSON = "INSERT INTO Lesson"
            + "(StartTime, EndTime, Location, ID_Course) VALUES"
            + "(?,?,?,?)";

    public LessonDAOUtils() {

    }

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
                                o[5] == null ? -1 : Long.parseLong(o[5].toString()),
                                o[6] == null ? null : o[6].toString(),
                                o[7] == null ? null : o[7].toString(),
                                o[8] == null ? null : o[8].toString(),
                                null,
                                o[9] == null ? null : o[9].toString(),
                                o[11] == null ? null : Integer.parseInt(o[11].toString()),
                                o[12] == null ? null : Double.parseDouble(o[12].toString())
                        )
                );

                lessons.add(lesson);
            }
        } catch (SQLException ex_sql) {
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
        }
        return lessons;
    }

    @Override
    public Lesson addLesson(Lesson lesson) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{lesson.getStartTime().getTime(), lesson.getEndTime().getTime(), lesson.getLocation(), lesson.getCourse().getId()};
        try {
            Object[] result = run.insert(QUERY_INSERT_LESSON, rsh, params);

            long id = Long.parseLong(result[0].toString());

            lesson.setId(id);
            System.out.println("SQL Success, output:");
            System.out.println(id);
            return lesson;
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAOUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Failed to add lesson to db");
            return null;
        }
    }

    @Override
    public boolean editLesson(Lesson lesson) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeLesson(Lesson lesson) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}