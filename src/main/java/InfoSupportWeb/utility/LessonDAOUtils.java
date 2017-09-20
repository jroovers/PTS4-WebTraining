/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import static InfoSupportWeb.utility.CourseDAOUtils.QUERY_INSERT_COURSE;
import Interfaces.ILessonDAO;
import Model.Lesson;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.QueryRunner;

/**
 *
 * @author Jorian
 */
public class LessonDAOUtils implements ILessonDAO {

    static final String QUERY_INSERT_LESSON = "INSERT INTO Lesson"
		+ "(StartTime, EndTime, Location) VALUES"
		+ "(?,?,?)";
    
    public LessonDAOUtils() {
        
    }    
    
    @Override
    public List<Lesson> getLessons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lesson addLesson(Lesson lesson) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{lesson.getStartTime().getTime(), lesson.getEndTime().getTime(), lesson.getLocation()};
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
