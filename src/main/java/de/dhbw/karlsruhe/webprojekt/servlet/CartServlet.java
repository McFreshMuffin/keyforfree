package de.dhbw.karlsruhe.webprojekt.servlet;

import de.dhbw.karlsruhe.webprojekt.bean.BestellungBean;
import de.dhbw.karlsruhe.webprojekt.bean.GameBean;
import de.dhbw.karlsruhe.webprojekt.email.EmailSendingService;
import de.dhbw.karlsruhe.webprojekt.email.ObjectConverter;
import de.dhbw.karlsruhe.webprojekt.model.Benutzer;
import de.dhbw.karlsruhe.webprojekt.model.Bestellung;
import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.MessagingException;
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
    private List<Games> shoppingCart = null;
    private String currentPage = null;
    private int gameId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        initSession(request);
        request.setAttribute("cart", shoppingCart);
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
        shoppingCart = (ArrayList) session.getAttribute("shoppingCart");
    }

    private void deleteGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for (Games curGame : shoppingCart) {
            if (curGame.getGameId() == gameId) {
                shoppingCart.remove(curGame);
                request.setAttribute("cart", shoppingCart);
                request.setAttribute("currentPage", currentPage);
                session.setAttribute("sessionCart", shoppingCart);
                request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
            }
        }
    }

    private void addGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Games game = this.gameBean.findGameById(gameId);
        shoppingCart.add(game);
        request.setAttribute("cart", shoppingCart);
        request.setAttribute("currentPage", currentPage);
        session.setAttribute("shoppingCart", shoppingCart);
        request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
    }

    private void buyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userId = (long) session.getAttribute("userId");
        Benutzer benutzer = (Benutzer) session.getAttribute("user");
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        Date date = new Date();
        Bestellung b = new Bestellung(userId, totalPrice, date, benutzer, shoppingCart);

        ObjectConverter converter = new ObjectConverter();
        converter.convertToXml(b);
        converter.convertXmlToPdf(userId);
        
        EmailSendingService ess = new EmailSendingService();
        try {
            ess.generateEmail(benutzer.getEmail());
            ess.sendEmail();
        } catch (MessagingException ex) {
            Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bestellungBean.saveBestellung(b);
        session.setAttribute("shoppingCart", new ArrayList<Games>());
        request.getRequestDispatcher("/WEB-INF/success.jsp").forward(request, response);
    }
}
