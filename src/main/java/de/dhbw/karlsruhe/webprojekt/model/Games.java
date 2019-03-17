package de.dhbw.karlsruhe.webprojekt.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private long gameId;
    
    @Column(name = "REQ_ID")
    private long reqId;
    
    @Column(name = "CATERGORY_ID")
    private long categoryId;
    
    @Column(name = "GENRE_ID")
    private long genreId;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "RELEASE_DATE")
    private String releaseDate;
    
    @Column(name = "FSK")
    private int fsk;
    
    @Column(name = "PRICE")
    private double price;
    
    @Column(name = "ABOUT_TEXT")
    private String aboutText;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "IMAGE")
    private String image;
    
    @Column(name = "LANGUAGE")
    private String languages; 
    
    public String getReleaseYear(){
        return releaseDate.substring(3,7);
    }
    
    //@OneToOne
    //private Genre genre;    
    
    
    @ManyToMany(mappedBy="gameListe",fetch=FetchType.EAGER)
       private List<Bestellung> orderList;
}
