package de.dhbw.karlsruhe.webprojekt.servlet;

import de.dhbw.karlsruhe.webprojekt.bean.BestellungBean;
import de.dhbw.karlsruhe.webprojekt.bean.GameBean;
import de.dhbw.karlsruhe.webprojekt.email.EmailSendingService;
import de.dhbw.karlsruhe.webprojekt.email.ObjectConverter;
import de.dhbw.karlsruhe.webprojekt.model.Benutzer;
import de.dhbw.karlsruhe.webprojekt.model.Bestellung;
import de.dhbw.karlsruhe.webprojekt.model.Games;
import de.dhbw.karlsruhe.webprojekt.util.GamesHashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @EJB
    GameBean gameBean;

    @EJB
    BestellungBean bestellungBean;

    private HttpSession session = null;
    private GamesHashMap shoppingCart = null;
    private String currentPage = null;
    private int gameId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        initSession(request);
        String reqRecordsPerPage = request.getParameter("recordsPerPage");
        String reqCurrentPage = request.getParameter("currentPage");
        request.setAttribute("shoppingCart", shoppingCart);
        request.setAttribute("recordsPerPage", reqRecordsPerPage);
        request.setAttribute("currentPage", reqCurrentPage);
        request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        currentPage = request.getParameter("currentUrl");
        initSession(request);

        switch (action) {
            case "add":
                //int amount = Integer.parseInt(request.getParameter("amount"));
                gameId = Integer.parseInt(request.getParameter("id"));
                addGame(request, response);
                break;
            case "buy":
                buyCart(request, response);
                break;
            case "delete":
                gameId = Integer.parseInt(request.getParameter("id"));
                deleteGame(request, response);
                break;
        }
    }

    private void initSession(HttpServletRequest request) {
        session = request.getSession();
        shoppingCart = (GamesHashMap) session.getAttribute("shoppingCart");

    }

    private void addGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Games game = this.gameBean.findGameById(gameId);
        shoppingCart.addGame(game);
        request.setAttribute("currentPage", currentPage);
        session.setAttribute("shoppingCart", shoppingCart);
        response.sendRedirect("cart?" + currentPage);
    }

    private void deleteGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Games game = shoppingCart.getGameById(gameId);
        shoppingCart.removeGame(game);
        request.setAttribute("currentPage", currentPage);
        session.setAttribute("shoppingCart", shoppingCart);
        request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
    }

    
    private void buyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userId = (long) session.getAttribute("userId");
        Benutzer benutzer = (Benutzer) session.getAttribute("user");
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        Date date = new Date();
        ArrayList<Games> convertedShoppingCart = shoppingCart.convertToArrayList();
        Bestellung b = new Bestellung(userId, totalPrice, date, benutzer, convertedShoppingCart);
        this.bestellungBean.saveBestellung(b);
        ObjectConverter converter = new ObjectConverter();
        converter.convertToXml(b);
        converter.convertXmlToPdf(b.getBestellId());
        EmailSendingService ess = new EmailSendingService();
        Games freeGame = this.gameBean.findFreeGame();
        ess.createEmail("service.key4free@gmail.com", benutzer.getNachname(), b.getBestellId(), convertedShoppingCart, freeGame);
        ess.sendEmail();
        session.setAttribute("shoppingCart", new ArrayList<Games>());
        request.getRequestDispatcher("/WEB-INF/success.jsp").forward(request, response);
    }
}
