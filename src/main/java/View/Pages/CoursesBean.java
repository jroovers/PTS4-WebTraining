/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import Controller.CategoryService;
import Controller.CourseService;
import Controller.UserGroupService;
import Model.Category;
import Model.Course;
import Model.UserGroup;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Kyle van Raaij
 * @author Jeroen Roovers
 */
@Named(value = "coursesBean")
@ConversationScoped

public class CoursesBean implements Serializable {

    @Inject
    CourseService courseService;
    @Inject
    CategoryService catService;
    @Inject
    UserGroupService groupService;

    private String code;                // value in the "code" texfield
    private String name;                // value in the "name" texfield
    private String description;         // value in the "description" texfield
    private String requiredKnowledge;   // value in the "required knowledge" texfield
    private String cursusMaterial;      // value in the "cursus material" texfield
    private String timeInDays;          // value in the "time in days" texfield
    private String cost;                // value in the "cost" texfield
    private String location;            // value in the "location" texfield
    private String keywords;            // value in the keywords texfield

    private transient List<Course> courses;       // List of courses
    private transient Course course;    // Current course  

    private String selectedCode;        // Selected item in SelectOneMenu
    private boolean hasSelectedCourse;  // Determines if a group is selected

    private String newCatName;          // Name of new category to add
    private String selectedCat;         // Long value (as string) of category ID to delete
    private String newUserGroupName;    // Name of new usergroup to add
    private String selectedUserGroup;   // Long value (as string) of usergroup ID to delete

    private String selectedCatToAdd;     // Long value (as string) of category to add to course
    private String selectedCatToRemove;  // Long value (as string) of category to add to course
    private String selectedGroupToAdd;   // Long value (as string) of category to add to course
    private String selectedGroupToRemove;// Long value (as string) of category to add to course

    private transient List<Category> selectedCourseCategories;
    private transient List<UserGroup> selectedCourseUsergroups;
    
    private final static String REDIRECTCOURSE = "courses?faces-redirect=true";
    private final static String REDIRECTEDITCOURSE = "editcourse?faces-redirect=true";
    private final static String EDITCOURSE = "editcourse";

    @Inject
    private Conversation conversation;

    private void resetConversation() {
        endConversation();
        startConversation();
    }

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    /**
     * Method to call when the user wants to create a new training
     *
     * @return
     */
    public String onCreateNewItem() {
        code = "";
        name = "";
        description = "";
        requiredKnowledge = "";
        cursusMaterial = "";
        timeInDays = "";
        cost = "";
        location = "";
        keywords = "";
        hasSelectedCourse = false;
        endConversation();
        return REDIRECTEDITCOURSE;
    }

    /**
     * Method to call when the user wants to edit an exisiting training
     *
     * @return
     */
    public String onEditItem() {
        if (selectedCode != null) {
            resetConversation();
            setCourseData();
            hasSelectedCourse = true;
            return REDIRECTEDITCOURSE;
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kies eerst een cursus", ""));
            return null;
        }
    }

    /**
     * Method to call when the user wants to remove an exisiting training
     *
     * @return
     */
    public String onDeleteItem() {
        if (selectedCode != null) {
            resetConversation();
            for (Course c : courses) {
                if (selectedCode.equals(c.getCode())) {
                    courseService.removeCourse(c.getId());
                }
            }
            courses = courseService.getAllCourses();
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Verwijderd", ""));
            return REDIRECTCOURSE;
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kies eerst een cursus", ""));
            return REDIRECTCOURSE;
        }
    }

    private void processCourseEdit() {
        boolean exist = changeCourse();
        if (!exist) {
            addCourse();
        }
    }

    private void showSaveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Opgeslagen!", ""));
    }

    /**
     * Checks if the course already exists. If so then the course will be
     * changed in the database. if not then a new course will be added to the
     * database.
     */
    public String updateCourse() {
        processCourseEdit();
        endConversation();
        showSaveMessage();
        return REDIRECTCOURSE;
    }

    public String quickUpdateCourse() {
        processCourseEdit();
        showSaveMessage();
        startConversation();
        hasSelectedCourse = true;
        return REDIRECTEDITCOURSE;
    }

    /**
     * Checks in the list of courses if its the same as the current course. if
     * so it will delete that course and update the list with courses.
     */
    public void deleteCourse() {
        for (Course c : courses) {
            if (selectedCode.equals(c.getCode())) {
                courseService.removeCourse(c.getId());
            }
        }
        courses = courseService.getAllCourses();
    }

    /**
     * Checks if the course code is the same as the current textfield code. If
     * so it wil update the same course in the list. this will also be updated
     * in the database.
     *
     * @return true if succesfully changed, false if it failed.
     */
    public boolean changeCourse() {
        getCourses();
        boolean exist = false;
        for (Course c : courses) {
            if (this.code.equals(c.getCode())) {
                try {
                    c.setCode(code);
                } catch (NullPointerException ex) {
                    Logger.getLogger(AuthorizationBean.class.getName()).log(Level.INFO, ex.getMessage(), ex);
                }
                try {
                    c.setName(name);
                } catch (NullPointerException ex) {
                    Logger.getLogger(AuthorizationBean.class.getName()).log(Level.INFO, ex.getMessage(), ex);
                }
                try {
                    c.setDescription(description);
                } catch (NullPointerException ex) {
                    Logger.getLogger(AuthorizationBean.class.getName()).log(Level.INFO, ex.getMessage(), ex);
                }
                try {
                    c.setPriorKnowledge(splitText(requiredKnowledge));
                } catch (NullPointerException ex) {
                    Logger.getLogger(AuthorizationBean.class.getName()).log(Level.INFO, ex.getMessage(), ex);
                }
                try {
                    c.setCourseMaterials(cursusMaterial);
                } catch (NullPointerException ex) {
                    Logger.getLogger(AuthorizationBean.class.getName()).log(Level.INFO, ex.getMessage(), ex);
                }
                try {
                    c.setDurationInDays(Integer.parseInt(timeInDays));
                } catch (NullPointerException ex) {
                    Logger.getLogger(AuthorizationBean.class.getName()).log(Level.INFO, ex.getMessage(), ex);
                }
                try {
                    c.setCost(Double.parseDouble(cost));
                } catch (NullPointerException ex) {
                    Logger.getLogger(AuthorizationBean.class.getName()).log(Level.INFO, ex.getMessage(), ex);
                }
                try {
                    c.setKeyWords(splitText(keywords));
                } catch (NullPointerException ex) {
                    Logger.getLogger(AuthorizationBean.class.getName()).log(Level.INFO, ex.getMessage(), ex);
                }
                exist = true;
                courseService.editCourse(c);
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

            if (!description.isEmpty() && !keywords.isEmpty() && !requiredKnowledge.isEmpty() && !cursusMaterial.isEmpty() && !timeInDays.isEmpty() && !cost.isEmpty()) {

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

                Course course = new Course(this.code, this.name);
                course.setCost(nCost);
                course.setCourseMaterials(cursusMaterial);
                course.setDescription(description);
                course.setDurationInDays(nTimeIndDays);
                course.setPriorKnowledge(nRequiredKnowledge);
                course.setKeyWords(nKeywords);
                this.course = courseService.addCourse(course);

                return true;
            } else {
                this.course = courseService.addCourse(code, name);
                return true;
            }
        }
        courseService.getAllCourses();
        return true;
    }

    /**
     * Get courses from the database and make the so that the List can be used
     * in a SelectOneMenu's.
     *
     * @return List with SelectItems
     */
    public List<Course> getCourses() {
        courses = courseService.getAllCourses();
        return courses;
    }

    public List<Category> getSelectedCourseCategories() {
        selectedCourseCategories = catService.getCategoriesFromCourse(course);
        return selectedCourseCategories;
    }

    public List<UserGroup> getSelectedCourseUsergroups() {
        selectedCourseUsergroups = groupService.getUserGroupsFromCourse(course);
        return selectedCourseUsergroups;
    }

    public List<Category> getCategories() {
        return this.catService.getAllCategories();
    }

    public List<UserGroup> getUserGroups() {
        return this.groupService.getAllUserGroups();
    }

    public List<Category> getCategoriesToAdd() {
        return getCategories();
    }

    public List<UserGroup> getUserGroupsToAdd() {
        return getUserGroups();
    }

    public String onCreateNewCategory() {
        if (!newCatName.isEmpty()) {
            catService.addCategory(newCatName);
            return REDIRECTCOURSE;
        } else {
            return null;
        }
    }

    public String onDeleteCategory() {
        if (!selectedCat.isEmpty()) {
            Category selectedItem = new Category();
            selectedItem.setId(Long.parseLong(selectedCat));
            catService.removeCategory(selectedItem);
            return REDIRECTCOURSE;
        } else {
            return null;
        }
    }

    public String onCreateNewUserGroup() {
        if (!newUserGroupName.isEmpty()) {
            groupService.addUserGroup(newUserGroupName);
            return REDIRECTCOURSE;
        } else {
            return null;
        }
    }

    public String onDeleteUserGroup() {
        if (!selectedUserGroup.isEmpty()) {
            UserGroup selectedItem = new UserGroup();
            selectedItem.setId(Long.parseLong(selectedUserGroup));
            groupService.removeUserGroup(selectedItem);
            return REDIRECTCOURSE;
        } else {
            return null;
        }
    }

    public String onAddCategoryToCourse() {
        catService.addCategoryToCourse(Long.parseLong(selectedCatToAdd), course.getId());
        return EDITCOURSE;
    }

    public String onAddUserGroupToCourse() {
        groupService.addUserGroupToCourse(Long.parseLong(selectedGroupToAdd), course.getId());
        return EDITCOURSE;
    }

    public String onRemoveCategoryFromCourse() {
        catService.removeCategoryFromCourse(Long.parseLong(selectedCatToRemove), course.getId());
        return EDITCOURSE;
    }

    public String onRemoveUserGroupFromCourse() {
        groupService.removeUserGroupFromCourse(Long.parseLong(selectedGroupToRemove), course.getId());
        return EDITCOURSE;
    }

    public String onCancelEdit() {
        endConversation();
        return REDIRECTCOURSE;
    }

    /**
     * Sets all the data from course in the textfields. if a value is null it
     * will leave the textbox empty.
     */
    public void setCourseData() {
        courses = getCourses();
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
            this.requiredKnowledge = splitStringList(course.getPriorKnowledge());
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
            this.keywords = splitStringList(course.getKeyWords());
        } catch (NullPointerException ex) {
            this.keywords = "";
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
      //  boolean first = true;
        for (String s : list) {
//            if (!first) {
                value = value + ", ";
//            } else {
//                s:
//                value = s;
//            }
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

    public boolean isHasSelectedCourse() {
        return hasSelectedCourse;
    }

    public void setHasSelectedCourse(boolean hasSelectedCourse) {
        this.hasSelectedCourse = hasSelectedCourse;
    }

    public String getSelectedCategory() {
        return selectedCat;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCat = selectedCategory;
    }

    public String getNewCategoryName() {
        return newCatName;
    }

    public void setNewCategoryName(String newCategoryName) {
        this.newCatName = newCategoryName;
    }

    public String getNewUserGroupName() {
        return newUserGroupName;
    }

    public void setNewUserGroupName(String newUserGroupName) {
        this.newUserGroupName = newUserGroupName;
    }

    public String getSelectedUserGroup() {
        return selectedUserGroup;
    }

    public void setSelectedUserGroup(String selectedUserGroup) {
        this.selectedUserGroup = selectedUserGroup;
    }

    public String getSelectedCat() {
        return selectedCat;
    }

    public void setSelectedCat(String selectedCat) {
        this.selectedCat = selectedCat;
    }

    public String getSelectedCatToAdd() {
        return selectedCatToAdd;
    }

    public void setSelectedCatToAdd(String selectedCatToAdd) {
        this.selectedCatToAdd = selectedCatToAdd;
    }

    public String getSelectedCatToRemove() {
        return selectedCatToRemove;
    }

    public void setSelectedCatToRemove(String selectedCatToRemove) {
        this.selectedCatToRemove = selectedCatToRemove;
    }

    public String getSelectedGroupToAdd() {
        return selectedGroupToAdd;
    }

    public void setSelectedGroupToAdd(String selectedGroupToAdd) {
        this.selectedGroupToAdd = selectedGroupToAdd;
    }

    public String getSelectedGroupToRemove() {
        return selectedGroupToRemove;
    }

    public void setSelectedGroupToRemove(String selectedGroupToRemove) {
        this.selectedGroupToRemove = selectedGroupToRemove;
    }

}
