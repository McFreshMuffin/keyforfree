
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


@WebServlet(name = "SuchServlet", urlPatterns = {"/search"})
public class SuchServlet extends HttpServlet {

     @EJB
     GameBean gameBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        
        String begriff = request.getParameter("suche");
        String suchbegriff = begriff.toLowerCase();
        List<Games> sucheTreffer = gameBean.searchGames(suchbegriff);
        long anzItems = gameBean.anzTreffer(suchbegriff);
        request.getSession().setAttribute("sucheTreffer", sucheTreffer);
        
        request.setAttribute("suchbegriff", begriff);
        request.setAttribute("anzItems", anzItems);
        request.setAttribute("items", sucheTreffer);
        request.getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
    }   
}
