/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.karlsruhe.webprojekt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author xNoTe
 */
@Data
@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue
    private long userId;
    
    private String vorname;
    private String nachname;

    public User() {
    }

    public User(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }
}
