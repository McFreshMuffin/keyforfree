package de.dhbw.karlsruhe.webprojekt.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "GENRE")
public class Genre implements Serializable {

    @Id
    @Column(name = "GENRE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int genreId;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "genre", fetch = FetchType.LAZY)
    Games games;

    @Column(name = "INDIE")
    private int indie;

    @Column(name = "AKTION")
    private int aktion;

    @Column(name = "ADVENTURE")
    private int adventure;

    @Column(name = "CASUAL")
    private int casual;
    
    @Column(name = "STRATEGY")
    private int strategy;

    @Column(name = "RPG")
    private int rpg;

    @Column(name = "SIMULATION")
    private int simulation;

    @Column(name = "EARLY_ACCESS")
    private int earlyAccess;

    @Column(name = "FREE_TO_PLAY")
    private int freeToPlay;

    @Column(name = "SPORT")
    private int sport;

    @Column(name = "RACING")
    private int racing;

    @Column(name = "MASSIVE_MULTIPLAYER")
    private int massivelyMultiplayer;
}
