/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jorian
 */
@ManagedBean(name="obj")
@SessionScoped
public class User {
    
    private String name;
    private String username;
    private String password;
    
    public User(String name, String username){
        this.name = name;
        this.username = username;
    }
    
    public User(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String add(){
        System.out.println(username + " " + username);
        
        if(!"1".equals(username)){
            return "failurePage";
        }
        else{
            return "test";
        }
    }
    
    
}
