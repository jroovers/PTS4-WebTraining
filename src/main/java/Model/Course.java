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
    private int durationInDays;
    private double cost;

    public Course() {
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

}