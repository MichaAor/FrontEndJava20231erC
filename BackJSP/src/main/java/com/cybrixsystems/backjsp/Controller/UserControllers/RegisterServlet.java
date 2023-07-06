package com.cybrixsystems.backjsp.Controller.UserControllers;

import com.cybrixsystems.backjsp.Model.User;
import com.cybrixsystems.backjsp.Repository.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO ud;

    public void init() {
        ud = new UserDAOIMP();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String dni = request.getParameter("dni");
        String email = request.getParameter("emailReg");
        String psw = request.getParameter("passwordReg");
        User userToReg = new User(name,surname,dni,email,psw);

        User userSaved = ud.saveUser(userToReg);
        HttpSession session = request.getSession();
        RequestDispatcher rs = null;

        if(userSaved != null){
            session.setAttribute("email",userSaved.getEmail());
            session.setAttribute("loginOn",true);
            response.sendRedirect("userPanel");
        }else {
            request.setAttribute("registerError",true);
            rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request,response);
        }
    }

    public void destroy() {
    }
}