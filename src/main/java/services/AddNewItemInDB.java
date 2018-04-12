package services;

import dao.DatabaseConnection;
import models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;

/**
 * Created by volodya.khachatryan on 4/11/2018.
 */
public class AddNewItemInDB extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ((req.getParameter("name") != null && req.getParameter("description") != null && req.getParameter("price") != null)
                &&(!"".equals(req.getParameter("name")) && !"".equals(req.getParameter("description")) && !"".equals(req.getParameter("price")))) {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            int price = Integer.parseInt(req.getParameter("price"));

            Item item = new Item(name, description, price);
            DatabaseConnection.addItem(item);

            resp.sendRedirect("/workspace.jsp");
        } else {
            resp.sendRedirect("/workspace.jsp");
        }

    }
}
