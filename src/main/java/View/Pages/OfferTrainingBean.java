
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.CourseService;
import Controller.LessonService;
import Controller.UserService;
import Model.Course;
import Model.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jowelle
 */
@Named(value = "offerTrainingBean")
@RequestScoped
public class OfferTrainingBean 
{
    
    @Inject
    CourseService cs;
    @Inject
    UserService us;
    
    private String userName;
    private String email;
    private String phonenr;
               // Connection to the database

    private String code;                // value in the "code" texfield
    private String courseName;                // value in the "name" texfield
    private String description;         // value in the "description" texfield
    private String requiredKnowledge;   // value in the "required knowledge" texfield
    private String cursusMaterial;      // value in the "cursus material" texfield
    private String timeInDays;          // value in the "time in days" texfield
    private String cost;                // value in the "cost" texfield
    private String location;            // value in the "location" texfield
    private String keywords;            // value in the keywords texfield
    
    private List<Course> courses;       // List of courses
    private Course course;              // Current course  
    private String selectedCode;        // Selected item in SelectOneMenu

    
    public void setCs(CourseService cs) {
        this.cs = cs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequiredKnowledge(String requiredKnowledge) {
        this.requiredKnowledge = requiredKnowledge;
    }

    public void setCursusMaterial(String cursusMaterial) {
        this.cursusMaterial = cursusMaterial;
    }

    public void setTimeInDays(String timeInDays) {
        this.timeInDays = timeInDays;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setSelectedCode(String selectedCode) {
        this.selectedCode = selectedCode;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenr() {
        return phonenr;
    }

    public String getCode() {
        return code;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public String getRequiredKnowledge() {
        return requiredKnowledge;
    }

    public String getCursusMaterial() {
        return cursusMaterial;
    }

    public String getTimeInDays() {
        return timeInDays;
    }

    public String getCost() {
        return cost;
    }

    public String getLocation() {
        return location;
    }

    public String getKeywords() {
        return keywords;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course getCourse() {
        return course;
    }

    public String getSelectedCode() {
        return selectedCode;
    }

    
    
    public void addCourseAndUser() 
    {
        //Add user
        User user = new User(userName, phonenr, email,4);
        us.addUser(user);
        FacesContext context = FacesContext.getCurrentInstance();
        
        if (userName == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bedrijfsnaam moet ingevuld zijn", "!"));
        }
        if (email == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email moet ingevuld zijn", "!"));
        }
        if (phonenr == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Telefoon nummer moet ingevuld zijn", "!"));
        }
        
        //Add course
        if (!courseName.equals("") && !code.equals("")) {

            if (!description.isEmpty() && !requiredKnowledge.isEmpty() && !cursusMaterial.isEmpty() && !timeInDays.isEmpty() && !cost.isEmpty()) {

                double nCost = 0;
                int nTimeIndDays = 0;
                String[] nRequiredKnowledge = this.splitText(requiredKnowledge);

                try {
                    nTimeIndDays = Integer.parseInt(timeInDays);
                    nCost = Double.parseDouble(cost);
                } catch (NumberFormatException ex) {
                }

                Course course = new Course(this.code, this.courseName);
                course.setCost(nCost);
                course.setCourseMaterials(cursusMaterial);
                course.setDescription(description);
                course.setDurationInDays(nTimeIndDays);
                course.setPriorKnowledge(nRequiredKnowledge);
                course.setSupplier(userName);
                cs.addCourse(course);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Training is toegevoegd", "!"));
            } else {
                cs.addCourse(code, userName);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Training is toegevoegd", "!"));
            }
        }
    }

    
    public String[] splitText(String text) {
        text = text.toLowerCase();
        String[] splittedText = text.split("\\,");
        return splittedText;
    }
}
