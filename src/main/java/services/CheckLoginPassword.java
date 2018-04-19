package services;

import dao.Dao;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by volodya.khachatryan on 3/20/2018.
 */


public class CheckLoginPassword extends HttpServlet {
    private Dao dao;

    @Override
    public void init() throws ServletException {
        this.dao = new Dao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/workspace.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = dao.getUser(username, password);

        if (user == null) {

            request.getRequestDispatcher("/loginPage.jsp").forward(request, response);
        } else {

            Cookie cookieUser = new Cookie("user", username);
            response.addCookie(cookieUser);

            Cookie cookiePassword = new Cookie("password", password);
            response.addCookie(cookiePassword);

            request.getSession().setAttribute("userBean", user);


            response.sendRedirect("/workspace.jsp");
        }

    }
}
