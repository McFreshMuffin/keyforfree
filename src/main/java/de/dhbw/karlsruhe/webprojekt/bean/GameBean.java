package de.dhbw.karlsruhe.webprojekt.bean;

import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public class GameBean {

    @PersistenceContext
    EntityManager em;

    public Games findGameById(int gameId){
        return em.find(Games.class, gameId);
    }
    
    public List<Games> findTop10Newest(){
       return em.createQuery("SELECT g FROM Games g ORDER BY g.ReleaseDate").setMaxResults(10).getResultList();
    }
    
    public List<Games> findGames(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        return em.createQuery("SELECT g FROM Games g ORDER BY g.ReleaseDate").setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
    }
    
    public long getNumberOfRows(){
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Games.class)));
        return em.createQuery(cq).getSingleResult();
        
        //return em.createQuery("SELECT COUNT(Id) FROM Countries");
    } 
}
