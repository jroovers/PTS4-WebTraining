/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Lesson;
import java.util.List;

/**
 *
 * @author Jorian
 */
public interface ILessonDAO {
       
    /**
     * Gets a list of lessons from database.
     *
     * @return Lesson List
     */
    public List<Lesson> getLessons();
    
    /**
     * Adds lesson to database.
     *
     * @return Return lesson with database ID
     */
    public Lesson addLesson(Lesson lesson);
    
    /**
     * Edits lesson in database.
     *
     * @return Success or failure
     */
    public boolean editLesson(Lesson lesson);
    
    /**
     * Removes lesson in database.
     *
     * @return Success or failure
     */
    public boolean removeLesson(Lesson lesson);
}
