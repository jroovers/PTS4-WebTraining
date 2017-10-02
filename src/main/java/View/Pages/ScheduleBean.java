/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import InfoSupportWeb.utility.CourseDAOUtils;
import Model.Course;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Jeroen Roovers
 */
@Named(value = "scheduleBean")
@RequestScoped
public class ScheduleBean 
{   /**
     * Creates a new instance of scheduleBean
     */
    
    private Course[] courseList;
    private String courseId;
    private List<Course> courses;
    private List<Course> allCourses;
    
    private CourseDAOUtils courseDAOUtils;
    
    public ScheduleBean() 
    {
        this.courseDAOUtils = new CourseDAOUtils();
    }
    
    public String getCourseId()
    {
        return courseId;
    }
    
    public Course[] getCourseValue() 
    {
        allCourses = new ArrayList<>(courseDAOUtils.getCourses());
        courses = new ArrayList<>();
        
        for(Course course : allCourses)
        {
            if(course.getDurationInDays() > 1)
            {
                courses.add(course);
                //courses.add(new SelectItem(course.getName(), Integer.toString(course.getDurationInDays()), course.getCourseMaterials()));
            }
        }
        
        Course[] courseArray = new Course[courses.size()];
        courseArray = courses.toArray(courseArray);
        
        return courseArray;
    }
    
    public Course[] getTrainingValue() 
    {
        allCourses = new ArrayList<>(courseDAOUtils.getCourses());
        courses = new ArrayList<>();
        
        for(Course course : allCourses)
        {
            if(course.getDurationInDays() == 1)
            {
                courses.add(course);
            }
        }
        
        Course[] courseArray = new Course[courses.size()];
        courseArray = courses.toArray(courseArray);
        
        return courseArray;
    }

    
}
