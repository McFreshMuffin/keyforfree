package de.dhbw.karlsruhe.webprojekt.bean;

import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GameBean {

    @PersistenceContext
    EntityManager em;

    /*public Games findGame(long gameId){
        return em.find(Games.class, gameId);
    }*/
    
    public List<Games> findAllGames(){
        return em.createQuery("SELECT g FROM Games g ORDER BY g.ReleaseDate").setMaxResults(50).getResultList();
    }
}
