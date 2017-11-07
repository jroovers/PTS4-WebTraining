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
    public boolean removeLesson(long lesson_ID);
    
    /**
     * Gets lessons from one course
     * 
     * @param course_ID the id of the course
     * @return List of lessons who are connected to the given id of the course
     */
    public List<Lesson> getLessonsFromCourse(long course_ID);
    
     /**
     * gets all lesson that a teacher gives
     * each lesson has the who users signed up for it
     * @param user_ID the userID of the teacher
     * @return  list of lessons scheduled to be given by the teacher
     */
    public List<Lesson> GetLessonAndRegistrationsByTeacher(long user_ID);

    /**
     * Signs an user to the Database on the lesson he/she selected.
     * 
     * @param lesson_ID id of the lesson selected
     * @param user_ID id of the user logged in
     */
    public long signUpUser(long lesson_ID, long user_ID);
}
