package com.cybrixsystems.backjsp.Controller.UserControllers;

import com.cybrixsystems.backjsp.Model.User;
import com.cybrixsystems.backjsp.Repository.UserDAO;
import com.cybrixsystems.backjsp.Repository.UserDAOIMP;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserDAO ud;

    public void init() {
        ud = new UserDAOIMP();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        if(request.getSession().getAttribute("email") == null){
            String email = request.getParameter("emailLog");
            String psw = request.getParameter("passwordLog");
            User userLog = new User();userLog.setEmail(email); userLog.setPassword(psw);

            HttpSession session = request.getSession();
            RequestDispatcher rs = null;

            if(ud.loginUser(userLog)){
                session.setAttribute("email",userLog.getEmail());
                session.setAttribute("loginOn",true);
                response.sendRedirect("userPanel");
            }else{
                request.setAttribute("loginError",true);
                rs = request.getRequestDispatcher("index.jsp");
                rs.forward(request,response);
            }
    }

    public void destroy() {
    }
}