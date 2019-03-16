package de.dhbw.karlsruhe.webprojekt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="BENUTZER")
public class Benutzer {
    
    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "FIRST_NAME")
    private String vorname;
    
    @Column(name = "LAST_NAME")
    private String nachname;
    
    @Column(name = "ADDRESS")
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
