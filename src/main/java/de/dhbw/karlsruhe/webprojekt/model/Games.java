package de.dhbw.karlsruhe.webprojekt.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "RELEASE_DATE")
    private int releaseDate;
    
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
    
<<<<<<< HEAD
    @Column(name = "LANGUAGE")
    private String languages; 
    
    public String getReleaseYear(){
        return releaseDate.substring(3,7);
    }
=======
    @Column(name = "SPRACHE")
    private String sprache;
>>>>>>> database
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
    private Category category;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "GENRE_ID", referencedColumnName = "GENRE_ID")
    private Genre genre;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "REQ_ID", referencedColumnName = "REQ_ID")
    private Requirements requirements;

    @ManyToMany(mappedBy="gameListe",fetch=FetchType.EAGER)
       private List<Bestellung> orderList;

    public String getReleaseYear(){
        //return releaseDate.substring(0,4);
        return "2000";
    }
}
