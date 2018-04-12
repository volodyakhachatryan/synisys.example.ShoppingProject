package services;

import dao.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by volodya.khachatryan on 3/20/2018.
 */
public class DeleteUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("DeleteUser servlet doPost()");
        String username = "";

        username = (String) request.getSession().getAttribute("username");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("password");


        DatabaseConnection.deleteUser(username);

        request.getRequestDispatcher("/loginPage.jsp").forward(request, response);
//        response.sendRedirect("/loginPage.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
