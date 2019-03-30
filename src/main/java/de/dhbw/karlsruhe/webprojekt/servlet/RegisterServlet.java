package de.dhbw.karlsruhe.webprojekt.servlet;

import de.dhbw.karlsruhe.webprojekt.bean.UserBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

   @EJB
   private UserBean userBean;
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vorname = request.getParameter("first_name");
        String nachname = request.getParameter("last_name");
        String addresse = request.getParameter("address");
        String confPasswort = request.getParameter("confirm_password");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean result = false;
        
        if (password.equals(confPasswort)) {
            if(this.userBean.addUser(email, password, vorname, nachname, addresse) != null){
                result = true;
            }
        }
        request.setAttribute("result", result);
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
}
