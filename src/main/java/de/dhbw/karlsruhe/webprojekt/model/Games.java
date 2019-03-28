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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;

@Data
@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@Table(name = "GAMES")
public class Games implements Serializable {

    public Games() {
    }
    
    /**
     * Konstruktor für GamesHashMapTest
     * @param gameId 
     */
    public Games(long gameId){
        this.gameId = gameId;
    }

    public Games(String name, String image) {
        this.name = name;
        this.image = image;
    }

    @Id
    @Column(name = "GAME_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gameId;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "RELEASE_DATE2")
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

    @Column(name = "SPRACHE")
    private String sprache;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
    private Category category;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "GENRE_ID", referencedColumnName = "GENRE_ID")
    private Genre genre;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "REQ_ID", referencedColumnName = "REQ_ID")
    private Requirements requirements;

    @ManyToMany(mappedBy = "gameListe", fetch = FetchType.EAGER)
    @XmlTransient
    private List<Bestellung> orderList;

    public String getReleaseYear(){
        return releaseDate.substring(0,4);
    }
}
