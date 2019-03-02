package de.dhbw.karlsruhe.webprojekt.servlet;

import de.dhbw.karlsruhe.webprojekt.bean.GameBean;
import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Startseite: Zeigt eine Übersicht der vorhandenen Textschnippsel sowie einen
 * Link zum Anlegsen neuer Schnippsel.
 */
@WebServlet(urlPatterns = "/index.html")
public class IndexServlet extends HttpServlet {

    public static final String URL = "/index.html";

    @EJB
    GameBean gameBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Vorhandene Schnippsel einlesen und im Request Context ablegen
        //List<Benutzer> allBenutzer = this.benutzerBean.findAllBenutzer();
        //request.setAttribute("allBenutzer", allBenutzer);

        /*
        Games game = this.gameBean.findGame(10);
        request.getSession().setAttribute("game", game);
        */

        List<Games> gameList = this.gameBean.findAllGames();
        request.getSession().setAttribute("allGames", gameList);

        // Anfrage an die index.jsp weiterleiten
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
