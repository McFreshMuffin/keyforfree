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
       return em.createQuery("SELECT g FROM Games g ORDER BY g.releaseDate desc").setMaxResults(10).getResultList();
    }
    
    public List<Games> findGames(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        return em.createQuery("SELECT g FROM Games g ORDER BY g.releaseDate").setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
    }
    
    public long getNumberOfRows(){
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Games.class)));
        return em.createQuery(cq).getSingleResult();
    }
    
    public List<Games> searchGames(String suchbegriff) {
        return em.createQuery("SELECT g FROM Games g WHERE lower(g.name) like '%"+suchbegriff+"%'").getResultList();
    }
    
    public long anzTreffer(String suchbegriff){
        return (long) em.createQuery("SELECT COUNT(g) FROM Games g WHERE lower(g.name) like '%"+suchbegriff+"%'").getSingleResult();
    }
}
