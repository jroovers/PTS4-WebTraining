/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.CourseService;
import Model.Course;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author Jeroen Roovers
 */
@Named(value = "coursesBean")
@RequestScoped
public class CoursesBean {

    @Inject
    CourseService cService;

    private String code;
    private String name;
    private String description;
    private String requiredKnowledge;
    private String cursusMaterial;
    private String timeInDays;
    private String cost;
    private String location;
    private String keywords;
    private List<Course> courses;
    private Course course;
    private String selectedCode;

    /**
     * Creates a new instance of coursesBean
     */
    public CoursesBean() {

        
    }

    public String getSelectedCode() {
        return selectedCode;
    }

    public void setSelectedCode(String _selectedCode) {
        this.selectedCode = _selectedCode;
    }
    
    

    
    
    
    public List<SelectItem> getCoursesCmb() {
        
        List<SelectItem> listCmb = new ArrayList<SelectItem>();
        courses = this.cService.getAllCourses();
        for(Course c : courses) {
            listCmb.add(new SelectItem(c.getCode(), c.getName()));
        }
        return listCmb;
    }
    
    
    
    public void setCourseData(){
        int number = 1;
        for(Course c : courses) {
            if(selectedCode.equals(c.getCode()))
            {
                course = c;
                break;
            }
        }
        
        try{ this.code = course.getCode(); } catch(NullPointerException ex) { this.code = ""; }
        try{ this.name = course.getName(); } catch(NullPointerException ex) { this.name = ""; }
        try{ this.description = course.getDescription(); } catch(NullPointerException ex) { this.description = ""; }
        try{ this.requiredKnowledge = this.splitStringList(course.getPriorKnowledge()); } catch(NullPointerException ex) { this.requiredKnowledge = ""; }
        try{ this.cursusMaterial = course.getCourseMaterials(); } catch(NullPointerException ex) { this.cursusMaterial = ""; }
        try{ this.timeInDays = Integer.toString(course.getDurationInDays()); } catch(NullPointerException ex) { this.timeInDays = ""; }
        try{ this.cost = Double.toString(course.getCost()); } catch(NullPointerException ex) { this.cost = ""; }
        try{ this.location = course.getLocation(); } catch(NullPointerException ex) { this.location = ""; }
               
    } 
    

    // Adds a course to the database.
    public boolean addCourse() {

        if (!name.equals("") && !code.equals("")) {

            if (!description.isEmpty() && !keywords.isEmpty() && !requiredKnowledge.isEmpty() && !cursusMaterial.isEmpty() && !timeInDays.isEmpty() && !cost.isEmpty() && !location.isEmpty() && !requiredKnowledge.isEmpty()) {

                double nCost = 0;
                int nTimeIndDays = 0;
                String[] nRequiredKnowledge = this.splitText(requiredKnowledge);
                String[] nKeywords = this.splitText(keywords);
                
                try {
                    nTimeIndDays = Integer.parseInt(timeInDays);
                    nCost = Double.parseDouble(cost);
                } catch (NumberFormatException ex) {
                    return false;
                }

                Course cource = new Course(this.code, this.name);
                cource.setCost(nCost);
                cource.setCourseMaterials(cursusMaterial);
                cource.setDescription(description);
                cource.setDurationInDays(nTimeIndDays);
                cource.setPriorKnowledge(nRequiredKnowledge);
                cource.setLocation(location);
                cource.setKeyWords(nKeywords);
                cService.addCourse(cource);
                return true;
            } else {
                cService.addCourse(code, name);
                return true;
            }

        }
        return false;
    }
    
    // Splits the given text and puts it in a list. the text is split by comma's
    public String[] splitText(String text) {
        text = text.toLowerCase();
        String[] splittedText = text.split("\\,");
        return splittedText;
    } 
    
    public String splitStringList(String[] list) {
        String value = "";
        boolean first = true;
        for(String s : list) {
            if(first == false) {
                value = value + ", " + s;
            } else {
                value = s;
            }
        }
        return value;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    public String getTimeInDays() {
        return timeInDays;
    }

    public void setTimeInDays(String timeInDays) {
        this.timeInDays = timeInDays;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String amount) {
        this.cost = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
