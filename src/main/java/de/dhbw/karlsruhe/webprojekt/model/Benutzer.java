package de.dhbw.karlsruhe.webprojekt.model;

import java.io.Serializable;
import javax.persistence.Column;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;

@Data
@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@Table(name = "BENUTZER")
public class Benutzer implements Serializable {

    @Id
    @Column(name = "BENUTZER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long benutzerId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORT")
    private String password;

    @Column(name = "VORNAME")
    private String vorname;

    @Column(name = "NACHNAME")
    private String nachname;

    @Column(name = "ADDRESSE")
    private String addresse;

    @OneToMany(mappedBy = "benutzer", targetEntity = Bestellung.class,
            fetch = FetchType.EAGER)
    @XmlTransient
    List<Bestellung> bestellungListe;

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
