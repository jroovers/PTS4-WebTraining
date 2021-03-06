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
        this.name = name;
    }

    public Category(long id, String name) {
        
        this.id = Math.max(0, id);
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = Math.max(0, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.equals("")){
            this.name = name;
        }
    }
}
