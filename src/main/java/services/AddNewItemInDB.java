package services;

import dao.Dao;
import models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by volodya.khachatryan on 4/11/2018.
 */
public class AddNewItemInDB extends HttpServlet {
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
        if ((request.getParameter("name") != null && request.getParameter("description") != null && request.getParameter("price") != null)
                && (!"".equals(request.getParameter("name")) && !"".equals(request.getParameter("description")) && !"".equals(request.getParameter("price")))) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int price = Integer.parseInt(request.getParameter("price"));

            Item item = new Item(name, description, price);
            dao.addItem(item);

            response.sendRedirect("/workspace.jsp");
        } else {

            response.sendRedirect("/workspace.jsp");
        }

    }
}
