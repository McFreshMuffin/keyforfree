package de.dhbw.karlsruhe.webprojekt.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "GAMES")
public class Games implements Serializable {

    @Id
    @Column(name = "GAME_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gameId;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "RELEASE_DATE")
    private String releaseDate;
    
    @Column(name = "FSK")
    private int fsk;
    
    @Column(name = "CONTROLLER_SUPPORT")
    private String controllerSupport;
    
    
    private double Price;
    private String AboutText;
    private String DetailedDescrip;
    private String Image;
    private String SupportedLanguages;
    
    
    @ManyToMany(mappedBy="gameListe",fetch=FetchType.EAGER)
       private List<Bestellung> orderList; 
    
    public String getReleaseYear(){
        return releaseDate.substring(0,4);
    }
    //@OneToOne
    //private Genre genre;    
}
