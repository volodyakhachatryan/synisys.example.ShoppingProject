package filters;


import dao.Dao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by volodya.khachatryan on 3/16/2018.
 */
public class UserLoggedInFilter implements javax.servlet.Filter {
    Dao dao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        dao = new Dao();

    }

    // checks whether the user is logged in. if not redirects him to the login page
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String cookieUser = "";
        String cookiePassword = "";

        for (int i = 0; i < request.getCookies().length; i++) {
            if (Objects.equals(request.getCookies()[i].getName(), "user")) {
                cookieUser = request.getCookies()[i].getValue();
            }
            if (Objects.equals(request.getCookies()[i].getName(), "password")) {
                cookiePassword = request.getCookies()[i].getValue();
            }
        }
        if (cookieUser.isEmpty() && cookiePassword.isEmpty()) {
            response.sendRedirect("/loginPage.jsp");
        } else {
            if (dao.getUser(cookieUser, cookiePassword) == null) {
                response.sendRedirect("/loginPage.jsp");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }


    @Override
    public void destroy() {

    }
}
