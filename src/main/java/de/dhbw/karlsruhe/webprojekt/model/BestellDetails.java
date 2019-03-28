package de.dhbw.karlsruhe.webprojekt.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "BESTELL_DETAILS")
public class BestellDetails implements Serializable{
    
    @Id
    @Column(name = "BESTELL_DETAIL_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bestellDetailId;
    
    @Column(name = "BESTELL_ID")
    private long bestellId;
    
    @Column(name = "GAME_ID")
    private long gameId;
    
    @Column(name = "GAME_KEY")
    private String gameKey;
}
