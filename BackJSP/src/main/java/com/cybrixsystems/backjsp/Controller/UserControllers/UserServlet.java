package com.cybrixsystems.backjsp.Controller.UserControllers;

import com.cybrixsystems.backjsp.Model.User;
import com.cybrixsystems.backjsp.Repository.UserDAO;
import com.cybrixsystems.backjsp.Repository.UserDAOIMP;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "userSer",value = "/userSer")
public class UserServlet {
    private UserDAO ud;

    public void init() {
        ud = new UserDAOIMP();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/register":
                register(request, response);
                break;
            case "/login":
                login(request, response);
                break;
            case "/logout":
                logout(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession().getAttribute("email") != null){
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String dni = request.getParameter("dni");
            String email = request.getParameter("emailReg");
            String psw = request.getParameter("passwordReg");
            User userToReg = new User(name,surname,dni,email,psw);

            User userSaved = ud.saveUser(userToReg);
            HttpSession session = request.getSession();

            if(userSaved.getId() != null){
                session.setAttribute("email",userSaved.getEmail());
                response.sendRedirect("userPanel.jsp");
            }
        }
        request.setAttribute("mensajeError", "Datos Invalidos");
        response.sendRedirect("index.jsp");

    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession().getAttribute("email") != null){
            String email = request.getParameter("emailLog");
            String psw = request.getParameter("passwordLog");
            User userLog = new User();userLog.setEmail(email);

            HttpSession session = request.getSession();
            if(ud.loginUser(userLog)){
                session.setAttribute("email",userLog.getEmail());
                response.sendRedirect("userPanel.jsp");
            }
        }
        request.setAttribute("mensajeError", "Datos Invalidos");
        response.sendRedirect("index.jsp");
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("Index.jps");
    }

}
