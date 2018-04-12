package services;

import models.Item;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.swing.text.html.CSS.getAttribute;

/**
 * Created by volodya.khachatryan on 4/11/2018.
 */
public class AddItemToCart extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddItemToCart doPost()");

        User user = (User) req.getSession().getAttribute("userBean");
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        Integer price = Integer.valueOf(req.getParameter("price"));
        Integer count = Integer.valueOf(req.getParameter("count"));

        user.getShoppingCart().addItemToCart(new Item(name, desc, price), count);

        resp.sendRedirect("/workspace.jsp");
    }
}
