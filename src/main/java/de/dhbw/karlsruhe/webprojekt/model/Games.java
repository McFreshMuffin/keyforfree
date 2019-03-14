package de.dhbw.karlsruhe.webprojekt.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "games")
public class Games implements Serializable {

    @Id
    @GeneratedValue
    private int GameId;
    private String Name;
    private String ReleaseDate;
    private int RequiredAge;
    private String ControllerSupport;
    private String isFree;
    private String FreeVerAvail;
    private String PurchaseAvail;
    private String SubscriptionAvail;
    private String PlatformWindows;
    private String PlatformLinux;
    private String PlatformMac;
    private String PCReqsHaveMin;
    private String PCReqsHaveRec;
    private String LinuxReqsHaveMin;
    private String LinuxReqsHaveRec;
    private String MacReqsHaveMin;
    private String MacReqsHaveRec;
    private String CategorySinglePlayer;
    private String CategoryMultiplayer;
    private String CategoryCoop;
    private String CategoryMMO;
    private String CategoryInAppPurchase;
    private String CategoryIncludeSrcSDK;
    private String CategoryIncludeLevelEditor;
    private String CategoryVRSupport;
    private String GenreIsNonGame;
    private String GenreIsIndie;
    private String GenreIsAction;
    private String GenreIsAdventure;
    private String GenreIsCasual;
    private String GenreIsStrategy;
    private String GenreIsRPG;
    private String GenreIsSimulation;
    private String GenreIsEarlyAccess;
    private String GenreIsFreeToPlay;
    private String GenreIsSports;
    private String GenreIsRacing;
    private String GenreIsMassivelyMultiplayer;
    private String PriceCurrency;
    private double Price;
    private String AboutText;
    private String DetailedDescrip;
    private String Image;
    private String SupportedLanguages;
    private String PCMinReqsText;
    private String PCRecReqsText;
    private String LinuxMinReqsText;
    private String LinuxRecReqsText;
    private String MacMinReqsText;
    private String MacRecReqsText;
    
    public String getReleaseYear(){
        return ReleaseDate.substring(0,4);
    }
    
    //@OneToOne
    //private Genre genre;    
}
