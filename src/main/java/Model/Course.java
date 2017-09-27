/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Jeroen Roovers
 */
public class Course {

    private long id;
    private String code;
    private String name;
    private String description;
    private String[] priorKnowledge;
    private String courseMaterials;
    private String[] keyWords;
    private int durationInDays;
    private double cost;
    private String location;

 

    /**
     * A blank course
     */
    public Course() {
    }

    /**
     * Used to create a new course with the bare minimal input.
     *
     * @param code
     * @param name
     */
    public Course(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Used to create a instance of an existing course in the database.
     *
     * @param id
     * @param code
     * @param name
     * @param description
     * @param priorKnowledge
     * @param courseMaterials
     * @param durationInDays
     * @param cost
     */
    public Course(long id, String code, String name, String description, String[] priorKnowledge, String courseMaterials, int durationInDays, double cost) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.priorKnowledge = priorKnowledge;
        this.courseMaterials = courseMaterials;
        this.durationInDays = durationInDays;
        this.cost = cost;
    }
    
        public Course(long id, String code, String name, String description, String[] priorKnowledge, String courseMaterials, String[] keyWords, int durationInDays, double cost) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.priorKnowledge = priorKnowledge;
        this.courseMaterials = courseMaterials;
        this.keyWords = keyWords;
        this.durationInDays = durationInDays;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String[] getPriorKnowledge() {
        
        if(this.priorKnowledge == null){
        String[] myStringArray = new String[]{"Niet geladen"};
            return myStringArray;
        }
        
        return priorKnowledge;
    }

    public void setPriorKnowledge(String[] priorKnowledge) {
        this.priorKnowledge = priorKnowledge;
    }

    public String getCourseMaterials() {
        return courseMaterials;
    }

    public void setCourseMaterials(String courseMaterials) {
        this.courseMaterials = courseMaterials;
    }
    
    public String[] getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String[] keyWords) {
        this.keyWords = keyWords;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public String getShortString(){
        return this.code + " - " + this.name;
    }
    
    public String getLongString(){
        return this.name + " - " + this.location + " - Kosten: " + this.cost + " - Dagen: " + this.durationInDays;
    }
}
