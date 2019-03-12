package de.dhbw.karlsruhe.webprojekt.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private String PlatformWindows;
    private String PlatformLinux;
    private String PlatformMac;
    private String PCReqsHaveMin;
    private String PCReqsHaveRec;
    private String LinuxReqsHaveMin;
    private String LinuxReqsHaveRec;
    private String MacReqsHaveMin;
    private String MacReqsHaveRec;
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
    
    @ManyToMany(mappedBy="gameListe",fetch=FetchType.EAGER)
       private List<Bestellung> orderList; 
    
    public String getReleaseYear(){
        return ReleaseDate.substring(0,4);
    }
}
