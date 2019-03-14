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
@Table(name = "GENRE")
public class Genre {

    @Id
    @Column(name = "GENRE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long genreId;

    @Column(name = "INDIE")
    private boolean indie;

    @Column(name = "ACTION")
    private boolean action;

    @Column(name = "ADVENTURE")
    private boolean adventure;

    @Column(name = "CASUAL")
    private boolean casual;

    @Column(name = "RPG")
    private boolean rpg;

    @Column(name = "SIMULATION")
    private boolean simulation;

    @Column(name = "EARLY_ACCESS")
    private boolean earlyAccess;

    @Column(name = "FREE_TO_PLAY")
    private boolean freeToPlay;

    @Column(name = "SPORT")
    private boolean sport;

    @Column(name = "RACING")
    private boolean racing;

    @Column(name = "MASSIVE_MULTIPLAYER")
    private boolean massivelyMultiplayer;
}
