package services;

import dao.Dao;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by volodya.khachatryan on 4/13/2018.
 */
public class SaveShoppingCart extends HttpServlet{
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("userBean");
        int userId = user.getUserId();
        int itemId;
        int count;

        int i = 0;
        while (req.getParameter("item" + i + "Id") != null){
            itemId = Integer.parseInt(req.getParameter("item" + i + "Id"));
            count = Integer.parseInt(req.getParameter("item" + i + "Count"));
            dao.saveItemToCart(userId, itemId, count);
            i++;
        }

        resp.sendRedirect("/shoppingCart.jsp");
    }
}
