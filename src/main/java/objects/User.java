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
 * @author Jorian, Antonio
 */
@ManagedBean(name="obj")
@SessionScoped
public class User {
    
    private int userID;
    private String username;
    private String password;
    
    public User(int userID ,String username, String password){
        this.userID = userID;
        this.username = username;
        this.password = password;
    }
    
    public User(){
        
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
