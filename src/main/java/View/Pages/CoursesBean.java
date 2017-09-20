/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.CourseService;
import Model.Course;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Jeroen Roovers
 */
@Named(value = "coursesBean")
@RequestScoped
public class CoursesBean {
    
    private String code;
    private String name;
    private String description;
    private String requiredKnowledge;
    private String cursusMaterial;
    private int timeInDays;
    private int cost;
    private String location;
    
    private CourseService cService;
    

    /**
     * Creates a new instance of coursesBean
     */
    public CoursesBean() {
        cService = new CourseService();
    }
    
    
    // Adds a course to the database.
    public void addCourse() {

        if (!name.equals("") && !code.equals("")) {

            if (description.equals("") || requiredKnowledge.equals("") || cursusMaterial.equals("") || timeInDays != 0 || cost != -1 || location.equals("")) {
                
                
                Course cource = new Course(this.code, this.name);
                cource.setCost(cost);
                cource.setCourseMaterials(cursusMaterial);
                cource.setDescription(description);
                cource.setDurationInDays(0);
                //cource.setPriorKnowledge(priorKnowledge);
                //cService.addCourse(course);
            } else {
                cService.addCourse(code, name);
            }

        }

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredKnowledge() {
        return requiredKnowledge;
    }

    public void setRequiredKnowledge(String requiredKnowledge) {
        this.requiredKnowledge = requiredKnowledge;
    }

    public String getCursusMaterial() {
        return cursusMaterial;
    }

    public void setCursusMaterial(String cursusMaterial) {
        this.cursusMaterial = cursusMaterial;
    }

//    public String getTimeInDays() {
//       // return timeInDays;
//    }

    public void setTimeInDays(String timeInDays) {
        //this.timeInDays = timeInDays;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(String amount) {
        cost = Integer.parseInt(amount);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
}
