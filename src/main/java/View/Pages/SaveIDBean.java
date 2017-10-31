/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import javax.faces.bean.ApplicationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 *
 * @author Jowelle
 */
@Named(value = "saveIDBean")
@ApplicationScoped
public class SaveIDBean 
{
    // Nog een bean aangemaakt om te kijken of ik via @ApplicationScoped wel langer gegevens kan bewaren.
    // Moet nog verder testen
    
    private long lessonID;

    public SaveIDBean(long l) 
    {
        this.lessonID = l;
    }
    
    public SaveIDBean() 
    {
        
    }

    public long getLessonID() 
    {
        return lessonID;
    }

    public void setLessonID(long lessonID) 
    {
        this.lessonID = lessonID;
    }
    public void valueChanged(ValueChangeEvent e) 
    {
        lessonID = (long)e.getNewValue();
    }
}
