package com.cybrixsystems.backjsp.Controller.UserControllers;

import com.cybrixsystems.backjsp.Model.Sale;
import com.cybrixsystems.backjsp.Model.Ticket;
import com.cybrixsystems.backjsp.Model.User;
import com.cybrixsystems.backjsp.Repository.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "userPanel", value = "/userPanel")
public class UserPanelServlet extends HttpServlet {
    private UserDAO ud;
    private TicketDAO td;
    private SaleDAO sd;

    public void init() {
        ud = new UserDAOIMP();
        td = new TicketDAOIMP();
        sd = new SaleDAOIMP();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession() != null){
            String email = request.getSession().getAttribute("email").toString();

            Optional<User> userOpt = ud.findUserByEmail(email);
            List<Ticket> ticketList = td.findAllTicketsByEmail(email);
            List<Sale> saleList = sd.findAllSalesByEmail(email);
            RequestDispatcher rs = null;

            if(userOpt.isPresent()){
                request.setAttribute("user",userOpt.orElseThrow());
                request.setAttribute("tList",ticketList);
                request.setAttribute("sList",saleList);
            }
            rs = request.getRequestDispatcher("userPanel.jsp");

        rs.forward(request,response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    public void destroy() {
    }
}