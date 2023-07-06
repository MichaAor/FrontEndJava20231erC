package com.cybrixsystems.backjsp.Controller.UserControllers;

import com.cybrixsystems.backjsp.Model.User;
import com.cybrixsystems.backjsp.Repository.UserDAO;
import com.cybrixsystems.backjsp.Repository.UserDAOIMP;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.jsp");
    }

    public void destroy() {
    }
}