/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.karlsruhe.webprojekt.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author Uwe-Laptop
 */
@Data
@Entity(name = "BESTELLUNGEN")
public class Bestellung {

    @Id
    @Column(name = "BESTELL_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bestellId;

    @Column(name = "BENUTZER_ID")
    private long benutzerId;

    @Column(name = "GESAMT_PREIS", precision = 2)
    private double gesamtPreis;

    @Column(name = "BESTELL_DATUM")
    private Date bestellDatum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "BENUTZER_ID", referencedColumnName = "BENUTZER_ID")
    private Benutzer benutzer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BESTELL_DETAILS",
            joinColumns
            = @JoinColumn(name = "BESTELL_ID", referencedColumnName = "BESTELL_ID"),
            inverseJoinColumns
            = @JoinColumn(name = "GameId", referencedColumnName = "GameId")
    )
    private List<Games> gameListe;

    public Bestellung(long benutzerId, double gesamtPreis, Date bestellDatum, Benutzer benutzer, List<Games> gameListe) {
        this.benutzerId = benutzerId;
        this.gesamtPreis = gesamtPreis;
        this.bestellDatum = bestellDatum;
        this.benutzer = benutzer;
        this.gameListe = gameListe;
    }
    
    

}
