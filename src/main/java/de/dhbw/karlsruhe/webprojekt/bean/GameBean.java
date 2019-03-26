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
    
     public List<Games> findGamesByGenre(int currentPage, int recordsPerPage, String genre) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        return em.createQuery("SELECT g FROM Games g, Genre r WHERE g.genreId = r.genreId AND r."+genre+" = 1 ORDER BY g.releaseDate ").setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
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

    //Index des Substrings finden
    public int isSubstring(String s1, String s2) {
        int M = s1.length();
        int N = s2.length();
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (s2.charAt(i + j) != s1.charAt(j)) {
                    break;
                }
            }
            if (j == M) {
                return i;
            }
        }
        return -1;
    }

    public String getGenres(Games game) {
        String genre = "";
        String aktGenre = "";
        boolean firstSign = false;
        String allGenres;

        //Genre ist entweder 0 oder 1 alle Werte in einen String schreiben
        allGenres = String.valueOf(game.getGenre().getIndie()); //Indie Index 0
        allGenres = allGenres + String.valueOf(game.getGenre().getAktion()); //Aktion Index 1
        allGenres = allGenres + String.valueOf(game.getGenre().getAdventure()); //Adventure Index 2
        allGenres = allGenres + String.valueOf(game.getGenre().getCasual()); //Casual Index 3
        allGenres = allGenres + String.valueOf(game.getGenre().getStrategy()); //Strategy Index 4
        allGenres = allGenres + String.valueOf(game.getGenre().getRpg()); //RPG Index 5
        allGenres = allGenres + String.valueOf(game.getGenre().getSimulation()); //Simulation Index 6
        allGenres = allGenres + String.valueOf(game.getGenre().getEarlyAccess()); //Early Access Index 7
        allGenres = allGenres + String.valueOf(game.getGenre().getFreeToPlay()); //Free2Play Index 8
        allGenres = allGenres + String.valueOf(game.getGenre().getSport()); //Sport Index 9
        allGenres = allGenres + String.valueOf(game.getGenre().getRacing()); //Racing Index 10
        allGenres = allGenres + String.valueOf(game.getGenre().getMassivelyMultiplayer()); //Massive Multiplayer Index 11

        //Einzelne Stellen des Strings abfragen
        for (int t = 0; t < allGenres.length(); t++) {
            if (allGenres.charAt(t) == "0".charAt(0)) {
                //Genre nicht vorhanden. Kein Code ausfuehren
            } else {
                //Anhand des akutellen Index das aktuelle Genre suchen
                if (t == 0) {
                    aktGenre = "Indie";
                }
                if (t == 1) {
                    aktGenre = "Aktion";
                }
                if (t == 2) {
                    aktGenre = "Abenteuer";
                }
                if (t == 3) {
                    aktGenre = "Casual";
                }
                if (t == 4) {
                    aktGenre = "Strategie";
                }
                if (t == 5) {
                    aktGenre = "RPG";
                }
                if (t == 6) {
                    aktGenre = "Simulation";
                }
                if (t == 7) {
                    aktGenre = "Early Access";
                }
                if (t == 8) {
                    aktGenre = "Free to Play";
                }
                if (t == 9) {
                    aktGenre = "Sport";
                }
                if (t == 10) {
                    aktGenre = "Rennspiele";
                }
                if (t == 11) {
                    aktGenre = "Massive Multiplayer";
                }

                if (firstSign) {
                    genre = genre + ", " + aktGenre;
                } else {
                    firstSign = true;
                    genre = aktGenre;
                }
            }
        }

        return genre;
    }

    public String getCategories(Games game) {
        String categories = "";
        String aktCategory = "";
        String allCategories;
        boolean firstSign = false;

        //Werte fuer jede Kategorie (0 oder 1) in einen String schreiben
        allCategories = String.valueOf(game.getCategory().getSingleplayer()); //Single Player Index 0
        allCategories = allCategories + String.valueOf(game.getCategory().getMultiplayer()); //Multiplayer Index 1
        allCategories = allCategories + String.valueOf(game.getCategory().getCoop()); //Coop Index 2
        allCategories = allCategories + String.valueOf(game.getCategory().getMmo()); //MMO Index 3
        allCategories = allCategories + String.valueOf(game.getCategory().getInAppPurchase()); //In App Purchase Index 4
        allCategories = allCategories + String.valueOf(game.getCategory().getSrcSdk()); //SRC SDK Index 5
        allCategories = allCategories + String.valueOf(game.getCategory().getLevelEditor()); //Level Editor Index 6
        allCategories = allCategories + String.valueOf(game.getCategory().getVrSupport()); //VR Support Index 7

        //Einzelne Stellen des Strings abfragen
        for (int i = 0; i < allCategories.length(); i++) {
            if (allCategories.charAt(i) == "0".charAt(0)) {
                //Kategorie nicht vorhanden
            } else {
                if (i == 0) {
                    aktCategory = "Singleplayer";
                }
                if (i == 1) {
                    aktCategory = "Multiplayer";
                }
                if (i == 2) {
                    aktCategory = "Coop";
                }
                if (i == 3) {
                    aktCategory = "MMO";
                }
                if (i == 4) {
                    aktCategory = "In App Purchase";
                }
                if (i == 5) {
                    aktCategory = "SRC SDK";
                }
                if (i == 6) {
                    aktCategory = "Level Editor";
                }
                if (i == 7) {
                    aktCategory = "VR Support";
                }
                if (firstSign) {
                    categories = categories + ", " + aktCategory;
                } else {
                    firstSign = true;
                    categories = aktCategory;
                }
            }
        }

        return categories;
    }

}