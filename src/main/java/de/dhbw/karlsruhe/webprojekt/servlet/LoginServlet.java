package de.dhbw.karlsruhe.webprojekt.servlet;

import de.dhbw.karlsruhe.webprojekt.bean.UserBean;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    private UserBean userBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        String vorname = request.getParameter("first_name");
        String nachname = request.getParameter("last_name");
        String addresse = request.getParameter("address");
        String email = request.getParameter("email");
        String passwort = request.getParameter("password");
        String confPasswort = request.getParameter("confirm_password");
        String type = request.getParameter("type");

        if (passwort.equals(confPasswort)) {
            boolean result = this.userBean.registerUser(email, passwort, vorname, nachname, addresse);
            request.setAttribute("result", result);
            request.setAttribute("type", type);
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }else{
            boolean result = false;
            request.setAttribute("result", result);
            request.setAttribute("type", type);
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
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
