package de.dhbw.karlsruhe.webprojekt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="benutzer")
public class Benutzer {
    
    @Id
    @GeneratedValue
    private long userId;
    
    private String email;
    private String password;
    
    private String vorname;
    private String nachname;
    private String addresse;
    
    public Benutzer() {
    }

    public Benutzer(String email, String password, String vorname, String nachname, String addresse) {
        this.email = email;
        this.password = password;
        this.vorname = vorname;
        this.nachname = nachname;
        this.addresse = addresse;
    }

   
}
