package services;

import dao.DatabaseConnection;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by volodya.khachatryan on 3/20/2018.
 */
public class CheckLoginPassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = DatabaseConnection.getUser(username, password);

        if(user == null){
            response.sendRedirect("/loginPage.jsp");
        }else {
//            HttpSession session = request.getSession(true);
//            session.setAttribute("username", username);
//            session.setAttribute("password", password);

            Cookie cookieUser = new Cookie("user", username);
            response.addCookie(cookieUser);

            Cookie cookiePassword = new Cookie("password", password);
            response.addCookie(cookiePassword);

            request.getSession().setAttribute("userBean", user);

//            request.getRequestDispatcher("/workspace.jsp").forward(request, response);
            response.sendRedirect("/workspace.jsp");
        }

    }
}
