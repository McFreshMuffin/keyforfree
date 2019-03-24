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
    private long genreId;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "genre", fetch = FetchType.LAZY)
    Games games;

    @Column(name = "INDIE")
    private long indie;

    @Column(name = "AKTION")
    private long aktion;

    @Column(name = "ADVENTURE")
    private long adventure;

    @Column(name = "CASUAL")
    private long casual;
    
    @Column(name = "STRATEGY")
    private long strategy;

    @Column(name = "RPG")
    private long rpg;

    @Column(name = "SIMULATION")
    private long simulation;

    @Column(name = "EARLY_ACCESS")
    private long earlyAccess;

    @Column(name = "FREE_TO_PLAY")
    private long freeToPlay;

    @Column(name = "SPORT")
    private long sport;

    @Column(name = "RACING")
    private long racing;

    @Column(name = "MASSIVE_MULTIPLAYER")
    private long massivelyMultiplayer;
}
