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

@WebServlet(name = "ShopServlet", urlPatterns = {"/shop"})
public class ShopServlet extends HttpServlet {

    @EJB
    GameBean gameBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String genre;
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
        genre = String.valueOf(request.getParameter("genre"));
        
        List<Games> gameList;
        long nOfPages;
        
        if (genre.equals("null")) {
            gameList = this.gameBean.findGames(currentPage, recordsPerPage);
            long rows = this.gameBean.getNumberOfRows();
            nOfPages = rows / recordsPerPage;
            if (nOfPages % recordsPerPage > 0) {
                nOfPages++;
            }
        } else {
            gameList = this.gameBean.findGamesByGenre(currentPage, recordsPerPage, genre);
            long rows = this.gameBean.getNumberOfRows();

            nOfPages = rows / recordsPerPage;
            if (nOfPages % recordsPerPage > 0) {
                nOfPages++;
            }
        }

        request.setAttribute("games", gameList);
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        request.getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
    }
}
