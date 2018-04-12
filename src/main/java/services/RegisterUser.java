package services;

import dao.DatabaseConnection;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by volodya.khachatryan on 3/20/2018.
 */
public class RegisterUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("username") != null && request.getParameter("password") != null &&
                (request.getParameter("username").length() >= 6 && request.getParameter("password").length() >= 6)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int age = 0;
            if (request.getParameter("age") != null) {
                age = Integer.parseInt(request.getParameter("age"));
            }

            User user = new User(firstName, lastName, username, password, age, "user");

            boolean isUserSaved = DatabaseConnection.saveUser(user);
            if (isUserSaved) {
//                HttpSession session = request.getSession(true);
//                session.setAttribute("username", username);
//                session.setAttribute("password", password);

                Cookie cookieUser = new Cookie("user", username);
                response.addCookie(cookieUser);

                Cookie cookiePassword = new Cookie("password", password);
                response.addCookie(cookiePassword);

//                request.getRequestDispatcher("/loginPage.jsp").forward(request, response);
                response.sendRedirect("/loginPage.jsp");
            } else {
//                request.getRequestDispatcher("/registrationPage.jsp").forward(request, response);
                response.sendRedirect("/registrationPage.jsp");
            }
        } else {
//            request.getRequestDispatcher("/loginPage.jsp").forward(request, response);
            response.sendRedirect("/registrationPage.jsp");
        }


    }
}
