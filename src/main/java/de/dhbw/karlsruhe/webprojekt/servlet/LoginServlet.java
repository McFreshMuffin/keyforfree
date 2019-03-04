package de.dhbw.karlsruhe.webprojekt.servlet;

import de.dhbw.karlsruhe.webprojekt.bean.UserBean;
import de.dhbw.karlsruhe.webprojekt.model.User;
import java.io.IOException;
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

        String type = request.getParameter("type");

        switch (type) {
            case "login":
                loginUser(request, response, type);
                break;
            case "register":
                registerUser(request, response, type);
                break;
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        String pRequestType = request.getParameter("type");

        switch (pRequestType) {
            case "login":
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                break;
            case "register":
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                break;
            case "logout":
                logoutUser(request, response);
                break;
        }
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException {
        String vorname = request.getParameter("first_name");
        String nachname = request.getParameter("last_name");
        String addresse = request.getParameter("address");
        String confPasswort = request.getParameter("confirm_password");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean result = false;

        if (password.equals(confPasswort)) {
            this.userBean.registerUser(email, password, vorname, nachname, addresse);
            result = true;
        }
        request.setAttribute("result", result);
        request.setAttribute("type", type);
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response, String type) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = this.userBean.loginUser(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("index.html");
        } else {
            boolean result = false;
            request.setAttribute("result", result);
            request.setAttribute("type", type);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
