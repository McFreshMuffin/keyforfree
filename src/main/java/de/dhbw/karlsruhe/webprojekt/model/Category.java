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
    private int categoryId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.LAZY)
    Games games;

    @Column(name = "SINGLEPLAYER")
    private int singleplayer;
    
    @Column(name = "MULTIPLAYER")
    private int multiplayer;
    
    @Column(name = "COOP")
    private int coop;
    
    @Column(name = "MMO")
    private int mmo;
    
    @Column(name = "IN_APP_PURCHASE")
    private int inAppPurchase;
    
    @Column(name = "SRC_SDK")
    private int srcSdk;
    
    @Column(name = "LEVEL_EDITOR")
    private int levelEditor;
    
    @Column(name = "VR_SUPPORT")
    private int vrSupport;
}
