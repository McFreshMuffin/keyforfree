package de.dhbw.karlsruhe.webprojekt.servlet;

import de.dhbw.karlsruhe.webprojekt.bean.GameBean;
import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    /*
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        int gameId = Integer.parseInt(request.getParameter("id"));
        String currentPage = request.getParameter("currentUrl");
        HttpSession session = request.getSession();
        List<Games> shoppingCart = (ArrayList) session.getAttribute("shoppingCart");
        
        switch (action) {
            case "add":
                Games game = this.gameBean.findGameById(gameId);
                shoppingCart.add(game);
                request.setAttribute("cart", shoppingCart);
                request.setAttribute("currentPage", currentPage);
                session.setAttribute("shoppingCart", shoppingCart);
                request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
                break;
            /*case "delete":
                cart = sessionCart;
                for (Games curGame : cart) {
                    if (curGame.getGameId() == gameId) {
                        cart.remove(curGame);
                        request.setAttribute("cart", cart);
                        request.setAttribute("currentPage", currentPage);
                        session.setAttribute("sessionCart", cart);
                        request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
                    }
                }
                break;
        }
     */
 /*
        if (session.getAttribute("userId") != null) {
            ArrayList mycart = (ArrayList) session.getAttribute("itemlist");
            double value = (Double) session.getAttribute("total");
            int gameId = Integer.parseInt(request.getParameter("gameId"));
            Games game = this.gameBean.findGameById(gameId);
            if (game != null) {
                value += game.getPriceFinal();
                mycart.add(game);
                session.setAttribute("itemlist", mycart);
                session.setAttribute("total", value);
                response.sendRedirect("shop.jsp");
            }
        } else {
            response.sendRedirect("index.jsp");
        }
          {

        }
    }*/
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        HttpSession session = request.getSession();
        List<Games> shoppingCart = (ArrayList) session.getAttribute("shoppingCart");
        request.setAttribute("cart", shoppingCart);
        request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String action = request.getParameter("action");
        int gameId = Integer.parseInt(request.getParameter("id"));
        String currentPage = request.getParameter("currentUrl");
        HttpSession session = request.getSession();
        List<Games> shoppingCart = (ArrayList) session.getAttribute("shoppingCart");

        switch (action) {
            case "add":
                Games game = this.gameBean.findGameById(gameId);
                shoppingCart.add(game);
                request.setAttribute("cart", shoppingCart);
                request.setAttribute("currentPage", currentPage);
                session.setAttribute("shoppingCart", shoppingCart);
                request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
                break;
            /*case "delete":
                cart = sessionCart;
                for (Games curGame : cart) {
                    if (curGame.getGameId() == gameId) {
                        cart.remove(curGame);
                        request.setAttribute("cart", cart);
                        request.setAttribute("currentPage", currentPage);
                        session.setAttribute("sessionCart", cart);
                        request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
                    }
                }
                break;*/
        }
    }

}
