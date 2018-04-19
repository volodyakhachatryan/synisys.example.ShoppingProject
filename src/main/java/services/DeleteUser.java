package services;

import dao.Dao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by volodya.khachatryan on 3/20/2018.
 */
public class DeleteUser extends HttpServlet {
    Dao dao;

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

        System.out.println("DeleteUser servlet doPost()");
        String username = "";

        username = (String) request.getSession().getAttribute("username");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("password");

        dao.deleteUser(username);

        response.sendRedirect("/loginPage.jsp");
    }

}
