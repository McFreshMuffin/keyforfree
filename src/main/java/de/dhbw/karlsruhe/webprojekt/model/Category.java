/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "CATEGORY")
public class Category implements Serializable{
    
    @Id
    @Column(name = "CATEGORY_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.LAZY)
    Games games;

    @Column(name = "SINGLEPLAYER")
    private String singleplayer;
    
    @Column(name = "MULTIPLAYER")
    private String multiplayer;
    
    @Column(name = "COOP")
    private String coop;
    
    @Column(name = "MMO")
    private String mmo;
    
    @Column(name = "IN_APP_PURCHASE")
    private String inAppPurchase;
    
    @Column(name = "SRC_SDK")
    private String srcSdk;
    
    @Column(name = "LEVEL_EDITOR")
    private String levelEditor;
    
    @Column(name = "VR_SUPPORT")
    private String vrSupport;
}
