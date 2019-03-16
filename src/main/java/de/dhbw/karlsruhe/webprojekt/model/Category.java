/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "CATEGORY")
public class Category {
    
    @Id
    @Column(name = "CATEGORY_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    @Column(name = "SINGLEPLAYER")
    private boolean singleplayer;
    
    @Column(name = "MULTIPLAYER")
    private boolean multiplayer;
    
    @Column(name = "COOP")
    private boolean coop;
    
    @Column(name = "MMO")
    private boolean mmo;
    
    @Column(name = "IN_APP_PURCHASE")
    private boolean inAppPurchase;
    
    @Column(name = "SRC_SDK")
    private boolean srcSdk;
    
    @Column(name = "LEVEL_EDITOR")
    private boolean levelEditor;
    
    @Column(name = "VR_SUPPORT")
    private boolean vrSupport;
}
