/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public Games findGame(long gameId){
        return em.find(Games.class, gameId);
    }
    
    public List<Games> findAllGames(){
        return em.createQuery("SELECT g FROM games  g ORDER BY g.ReleaseDate ASC").setMaxResults(50).getResultList();
    }
}
