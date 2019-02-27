/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.karlsruhe.webprojekt.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 *
 * @author Uwe-Laptop
 */

@Entity
public class Benutzer implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "benutzer_ids")
    @TableGenerator(name = "benutzer_ids", initialValue = 0, allocationSize = 50)
    private int id;
    private String vorname;
    private String nachname;
    
    public Benutzer() {
    }

    public Benutzer(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }
    
    
}
