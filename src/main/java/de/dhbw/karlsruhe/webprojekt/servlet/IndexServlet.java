package de.dhbw.karlsruhe.webprojekt.servlet;

import de.dhbw.karlsruhe.webprojekt.bean.GameBean;
import de.dhbw.karlsruhe.webprojekt.email.EmailSendingService;
import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.io.IOException;
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

@WebServlet(urlPatterns = "/index.html")
public class IndexServlet extends HttpServlet {

    public static final String URL = "/index.html";

    @EJB
    GameBean gameBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Games> newestGames = gameBean.findTop10Newest();
        request.getSession().setAttribute("newestGames", newestGames);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
