/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.karlsruhe.webprojekt.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Uwe-Laptop
 */
@Stateless
public class ListBean {

    @PersistenceContext
    EntityManager em;

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
