package de.dhbw.karlsruhe.webprojekt.servlet;

import de.dhbw.karlsruhe.webprojekt.bean.UserBean;
import de.dhbw.karlsruhe.webprojekt.model.User;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    private UserBean userBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = this.userBean.loginUser(email, password);

        if (user != null) {
            ArrayList cart = new ArrayList();
            HttpSession session = request.getSession();
            session.setAttribute("email", user.getEmail());
            session.setAttribute("username", user.getVorname());
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("shoppingCart", cart);
            response.sendRedirect("index.html");
        } else {
            boolean result = false;
            request.setAttribute("result", result);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
    
     public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
