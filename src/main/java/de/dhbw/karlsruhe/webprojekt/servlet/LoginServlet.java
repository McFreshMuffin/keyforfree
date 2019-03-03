package de.dhbw.karlsruhe.webprojekt.servlet;

import de.dhbw.karlsruhe.webprojekt.bean.UserBean;
import de.dhbw.karlsruhe.webprojekt.model.User;
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        switch (type) {
            case "login":
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
                break;
            case "register":
                String vorname = request.getParameter("first_name");
                String nachname = request.getParameter("last_name");
                String addresse = request.getParameter("address");
                String confPasswort = request.getParameter("confirm_password");

                if (password.equals(confPasswort)) {
                    boolean result = this.userBean.registerUser(email, password, vorname, nachname, addresse);
                    request.setAttribute("result", result);
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                } else {
                    boolean result = false;
                    request.setAttribute("result", result);
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                }
                break;
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        String pRequestType = request.getParameter("type");
        String aRequestType = (String) request.getAttribute("type");

        if ("login".equals(pRequestType) || "login".equals(aRequestType)) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else if ("register".equals(pRequestType) || "register".equals(aRequestType)) {
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }
}
