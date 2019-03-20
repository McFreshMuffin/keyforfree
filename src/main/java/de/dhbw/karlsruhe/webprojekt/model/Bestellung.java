package de.dhbw.karlsruhe.webprojekt.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@XmlRootElement
@Table(name = "BESTELLUNGEN")
public class Bestellung implements Serializable {

    @Id
    @Column(name = "BESTELL_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bestellId;

    @Column(name = "BENUTZER_ID")
    private long benutzerId;

    @Column(name = "GESAMT_PREIS", precision = 6, scale = 2)
    private double gesamtPreis;

    @Column(name = "BESTELL_DATUM")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bestellDatum;

    @ManyToOne(optional = false)
    @ToString.Exclude
    @JoinColumn(name = "BENUTZER_ID", referencedColumnName = "BENUTZER_ID")
    private Benutzer benutzer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BESTELL_DETAILS",
            joinColumns
            = @JoinColumn(name = "BESTELL_ID", referencedColumnName = "BESTELL_ID"),
            inverseJoinColumns
            = @JoinColumn(name = "GAME_ID", referencedColumnName = "GAME_ID")
    )
    @ToString.Exclude
    private List<Games> gameListe;

    public Bestellung() {
    }

    public Bestellung(long benutzerId, double gesamtPreis, Date bestellDatum, Benutzer benutzer, List<Games> gameListe) {
        this.benutzerId = benutzerId;
        this.gesamtPreis = gesamtPreis;
        this.bestellDatum = bestellDatum;
        this.benutzer = benutzer;
        this.gameListe = gameListe;
    }
}
