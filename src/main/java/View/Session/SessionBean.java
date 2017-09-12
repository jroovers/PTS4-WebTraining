/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Session;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Jereoen Roovers
 */
@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    /**
     * Creates a new instance of sessionBean
     */
    public SessionBean() {
    }

}
