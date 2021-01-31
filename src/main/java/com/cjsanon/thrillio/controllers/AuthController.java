package com.cjsanon.thrillio.controllers;

import com.cjsanon.thrillio.managers.UserManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/auth", "/auth/logout"})
public class AuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AuthController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet path: " + request.getServletPath());
        if(!request.getServletPath().contains("logout")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            long userId = UserManager.getInstance().authenticate(email, password);
            if (userId != -1) {
                HttpSession session = request.getSession(); //session-id
                session.setAttribute("userId", userId);

                request.getRequestDispatcher("bookmark/mybooks").forward(request, response); //servlet does not need /
            } else {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            request.getSession().invalidate();
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
