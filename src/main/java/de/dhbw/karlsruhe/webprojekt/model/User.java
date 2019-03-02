package de.dhbw.karlsruhe.webprojekt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue
    private long userId;
    
    private String email;
    private String password;
    
    private String vorname;
    private String nachname;
    private String addresse;
    
    public User() {
    }

    public User(String email, String password, String vorname, String nachname, String addresse) {
        this.email = email;
        this.password = password;
        this.vorname = vorname;
        this.nachname = nachname;
        this.addresse = addresse;
    }

   
}
