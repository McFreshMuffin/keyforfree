package de.dhbw.karlsruhe.webprojekt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        
        
        
        
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        String requestType = request.getParameter("type");

        if ("login".equals(requestType)) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            System.out.print("DO GET!");
        }else if("register".equals(requestType)){
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            System.out.print("DO POST!");
        }
    }
}
