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

    public Games findGameById(int gameId) {
        return em.find(Games.class, gameId);
    }

    public List<Games> findTop10Newest() {
        return em.createQuery("SELECT g FROM Games g ORDER BY g.ReleaseDate desc").setMaxResults(10).getResultList();
    }

    public List<Games> findGames(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        return em.createQuery("SELECT g FROM Games g ORDER BY g.ReleaseDate").setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
    }

    public long getNumberOfRows() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Games.class)));
        return em.createQuery(cq).getSingleResult();

        //return em.createQuery("SELECT COUNT(Id) FROM Countries");
    }

    public Games checkRequirements(Games game) {
        String rec = "Recommended";
        int index = isSubstring(rec, game.getPCMinReqsText());
        if (index == -1) {
            game.setPCReqsHaveRec("True");
            game.setPCRecReqsText("GameBean Error: Method checkRequirements()");
            return em.merge(game);
        } else {
            String test = game.getPCMinReqsText().substring(index);
            game.setPCRecReqsText(test);
            test = game.getPCMinReqsText().substring(0, index);
            game.setPCMinReqsText(test);
            game.setPCReqsHaveRec("True");
            return em.merge(game);
        }
    }

    public int isSubstring(String s1, String s2) {
        int M = s1.length();
        int N = s2.length();
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) 
                if (s2.charAt(i + j) != s1.charAt(j)) 
                    break;
                
                if (j == M) 
                    return i;
                
            
        }
        return -1;
    }
}
