package services;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by volodya.khachatryan on 3/20/2018.
 */
public class SignOut extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getSession().removeAttribute("username");
//        request.getSession().removeAttribute("password");

        for (int i = 0; i < request.getCookies().length; i++) {
            if (Objects.equals(request.getCookies()[i].getName(), "user")) {
                Cookie cookieUser = new Cookie("user", "");
                cookieUser.setMaxAge(0);
                response.addCookie(cookieUser);

                Cookie cookiePassword = new Cookie("password", "");
                cookiePassword.setMaxAge(0);
                response.addCookie(cookiePassword);


            }
        }

//        request.getRequestDispatcher("/loginPage.jsp").forward(request, response);
        response.sendRedirect("/loginPage.jsp");
    }
}
