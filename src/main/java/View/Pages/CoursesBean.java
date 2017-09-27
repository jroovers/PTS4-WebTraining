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
 * @author Kyle van Raaij
 */
@Named(value = "coursesBean")
@RequestScoped
public class CoursesBean {

    @Inject
    CourseService cService;             // Connection to the database

    private String code;                // value in the "code" texfield
    private String name;                // value in the "name" texfield
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

    /**
     * Creates a new instance of coursesBean
     */
    public CoursesBean() {

    }

    /**
     * Checks if the course already exists. If so then the course will be
     * changed in the database. if not then a new course will be added to the
     * database.
     */
    public void updateCourse() {
        boolean exist = changeCourse();

        if (!exist) {
            addCourse();
        }
    }

    /**
     * Checks in the list of courses if its the same as the current course. if
     * so it will delete that course and update the list with courses.
     */
    public void deleteCourse() {
        for (Course c : courses) {
            if (selectedCode.equals(c.getCode())) {
                cService.removeCourse(c.getId());
            }
        }
        courses = cService.getAllCourses();
    }

    /**
     * Checks if the course code is the same as the current textfield code. If
     * so it wil update the same course in the list. this will also be updated
     * in the database.
     *
     * @return true if succesfully changed, false if it failed.
     */
    public boolean changeCourse() {
        boolean exist = false;
        for (Course c : courses) {
            if (this.code.equals(c.getCode())) {
                try {
                    c.setCode(code);
                } catch (NullPointerException ex) {
                }
                try {
                    c.setName(name);
                } catch (NullPointerException ex) {
                }
                try {
                    c.setDescription(description);
                } catch (NullPointerException ex) {
                }
                try {
                    c.setPriorKnowledge(splitText(requiredKnowledge));
                } catch (NullPointerException ex) {
                }
                try {
                    c.setCourseMaterials(cursusMaterial);
                } catch (NullPointerException ex) {
                }
                try {
                    c.setDurationInDays(Integer.parseInt(timeInDays));
                } catch (NullPointerException ex) {
                }
                try {
                    c.setCost(Double.parseDouble(cost));
                } catch (NullPointerException ex) {
                }
                try {
                    c.setLocation(location);
                } catch (NullPointerException ex) {
                }
                exist = true;
                cService.editCourse(c);
            }
        }
        return exist;
    }

    /**
     * Checks if all the fields are filled in. If not it looks if name and code
     * are filled in. if so it will add a new course to the database with only
     * those two values. If all fields are filled in it will add a new course to
     * the database with all the values.
     *
     * @return true if course is succesfully added to the database, false if it
     * failed.
     */
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
        cService.getAllCourses();
        return true;
    }

    /**
     * Get courses from the database and make the so that the List can be used
     * in a SelectOneMenu's.
     *
     * @return List with SelectItems
     */
    public List<SelectItem> getCoursesCmb() {

        List<SelectItem> listCmb = new ArrayList<>();
        courses = this.cService.getAllCourses();
        for (Course c : courses) {
            listCmb.add(new SelectItem(c.getCode(), c.getName()));
        }
        return listCmb;
    }

    /**
     * Sets all the data from course in the textfields. if a value is null it
     * will leave the textbox empty.
     */
    public void setCourseData() {
        int number = 1;
        for (Course c : courses) {
            if (selectedCode.equals(c.getCode())) {
                course = c;
                break;
            }
        }
        try {
            this.code = course.getCode();
        } catch (NullPointerException ex) {
            this.code = "";
        }
        try {
            this.name = course.getName();
        } catch (NullPointerException ex) {
            this.name = "";
        }
        try {
            this.description = course.getDescription();
        } catch (NullPointerException ex) {
            this.description = "";
        }
        try {
            this.requiredKnowledge = this.splitStringList(course.getPriorKnowledge());
        } catch (NullPointerException ex) {
            this.requiredKnowledge = "";
        }
        try {
            this.cursusMaterial = course.getCourseMaterials();
        } catch (NullPointerException ex) {
            this.cursusMaterial = "";
        }
        try {
            this.timeInDays = Integer.toString(course.getDurationInDays());
        } catch (NullPointerException ex) {
            this.timeInDays = "";
        }
        try {
            this.cost = Double.toString(course.getCost());
        } catch (NullPointerException ex) {
            this.cost = "";
        }
        try {
            this.location = course.getLocation();
        } catch (NullPointerException ex) {
            this.location = "";
        }
    }

    /**
     * Makes a list with string values. the given text seperates the text by
     * comma's and will be put in a list.
     *
     * @param text text
     * @return List with strings
     */
    public String[] splitText(String text) {
        text = text.toLowerCase();
        String[] splittedText = text.split("\\,");
        return splittedText;
    }

    /**
     * Makes a string with all the values in the given list. The values are
     * seperated by comma's.
     *
     * @param list list of strings
     * @return String with all the list values splitted by an comma.
     */
    public String splitStringList(String[] list) {
        String value = "";
        boolean first = true;
        for (String s : list) {
            if (!first) {
                value = value + ", ";
            } else {
                s:
                value = s;
            }
        }
        return value;
    }

    public String getSelectedCode() {
        return selectedCode;
    }

    public void setSelectedCode(String _selectedCode) {
        this.selectedCode = _selectedCode;
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
