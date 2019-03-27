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
        return em.createQuery("SELECT g FROM Games g ORDER BY g.releaseDate desc").setMaxResults(10).getResultList();
    }

    public List<Games> findGames(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        return em.createQuery("SELECT g FROM Games g ORDER BY g.releaseDate").setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
    }

    public long getNumberOfRows() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Games.class)));
        return em.createQuery(cq).getSingleResult();
    }

    public Games checkRequirements(Games game) {
        String rec = "Recommended";
        String min = "Minimum";
        String changeReq;
        
        //Requirements im Array speichern fuer Schleife 
        String[][] reqs = new String [3][3];
        //PC Requirements
        reqs[0] [0] = new String();
        reqs[0] [0] = Integer.toString(game.getRequirements().getHaveRecPcReqs());
        reqs[0] [1] = new String();
        reqs[0] [1] = game.getRequirements().getPCMinReqsText();
        reqs[0] [2] = new String();
        reqs[0] [2] = game.getRequirements().getPCRecReqsText();
        //Linux Requirements
        reqs[1] [0] = new String();
        reqs[1] [0] = Integer.toString(game.getRequirements().getHaveRecLinuxReqs());
        reqs[1] [1] = new String();
        reqs[1] [1] = game.getRequirements().getLinuxMinReqsText();
        reqs[1] [2] = new String();
        reqs[1] [2] = game.getRequirements().getLinuxRecReqsText();
        //Mac Requirements
        reqs[2] [0] = new String();
        reqs[2] [0] = Integer.toString(game.getRequirements().getHaveRecMacReqs());
        reqs[2] [1] = new String();
        reqs[2] [1] = game.getRequirements().getMacMinReqsText();
        reqs[2] [2] = new String();
        reqs[2] [2] = game.getRequirements().getMacRecReqsText();
        
        //Mit Schleife durch alle Requirements gehen und Recommended und Minimum kuerzen
        for (int r = 0; r < 3; r++) {
            int index = isSubstring(rec, reqs[r][1]);
            if (index == -1) {
                //Bei isSubstring = -1 Substring nicht vorhanden
            } else {
                changeReq = reqs[r][1].substring(index);
                reqs[r][2] = changeReq;
                changeReq = reqs[0][1].substring(0, index);
                reqs[r][1] = changeReq;
                reqs[r][0] = "1";
            }
            index = isSubstring(min, reqs[r][1]);
            if (index != -1) {
                changeReq = reqs[r][1].substring(8); //Index 9 ohne Wort Minimum:
                reqs[r][1] = changeReq;
            }
            index = isSubstring(rec, reqs[r][2]);
            if (index != -1) {
                changeReq = reqs[r][2].substring(12); //Index 12 ohne Wort Recommended:
                reqs[r][2] = changeReq;
            }
        }
        //Pc Requirements
        game.getRequirements().setHaveRecPcReqs(Integer.parseInt(reqs[0][0]));
        game.getRequirements().setPCMinReqsText(reqs[0][1]);
        game.getRequirements().setPCRecReqsText(reqs[0][2]);
        //Linux Requirements
        game.getRequirements().setHaveRecLinuxReqs(Integer.parseInt(reqs[1][0]));
        game.getRequirements().setLinuxMinReqsText(reqs[1][1]);
        game.getRequirements().setLinuxRecReqsText(reqs[1][2]);
        //Mac Requirements
        game.getRequirements().setHaveRecMacReqs(Integer.parseInt(reqs[2][0]));
        game.getRequirements().setMacMinReqsText(reqs[2][1]);
        game.getRequirements().setMacRecReqsText(reqs[2][2]);
        
        return em.merge(game);
    }

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