package services;

import dao.Dao;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by volodya.khachatryan on 3/20/2018.
 */
public class RegisterUser extends HttpServlet {
    Dao dao;

    @Override
    public void init() throws ServletException {
        dao = new Dao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/workspace.jsp");
    }

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

            User user = new User(firstName, lastName, username, password, "user", age);

            boolean isUserSaved = dao.saveUser(user);
            if (isUserSaved) {

                Cookie cookieUser = new Cookie("user", username);
                response.addCookie(cookieUser);

                Cookie cookiePassword = new Cookie("password", password);
                response.addCookie(cookiePassword);

                response.sendRedirect("/loginPage.jsp");
            } else {

                response.sendRedirect("/registrationPage.jsp");
            }
        } else {

            response.sendRedirect("/registrationPage.jsp");
        }
    }
}
