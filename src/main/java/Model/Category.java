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
public class Category {

    private long id;
    private String name;

    public Category() {

    }

    public Category(String name) {
    }

    public Category(long id, String name) {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongString() {
        return this.id + " - " + this.name;
    }

}
