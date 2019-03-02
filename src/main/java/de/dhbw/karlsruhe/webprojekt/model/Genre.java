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
@Table(name="genre")
public class Genre {
    
    @Id
    @GeneratedValue
    private long genreId;
    private boolean indie;
    private boolean action;
    private boolean adventure;
    private boolean casual;
    private boolean rpg;
    private boolean simulation;
    private boolean earlyAccess;
    private boolean freeToPlay;
    private boolean sport;
    private boolean racing;
    private boolean massivelyMultiplayer;
}
