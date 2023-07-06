package com.cybrixsystems.backjsp.Controller.TicketController;

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
import java.util.Optional;

@WebServlet(name = "bTicket", value = "/bTicket")
public class BuyTicketServlet extends HttpServlet {
    private TicketDAO td;
    private SaleDAO sd;

    public void init() {
        td = new TicketDAOIMP();
        sd = new SaleDAOIMP();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getParameter("emailTicket") != null &&
           request.getParameter("amountTicket") != null &&
           request.getParameter("categoryTicket") != null) {

            String email = request.getParameter("emailTicket");
            int amount = Integer.parseInt(request.getParameter("amountTicket"));
            String category = request.getParameter("categoryTicket");

            Ticket ticket = td.butTicket(category,amount,email);
            Sale saleSaved = null;
            HttpSession session = request.getSession();
            RequestDispatcher rs = null;

            if(ticket != null){

                User user = new User();
                user.setEmail(email);
                Sale saleToSave = new Sale();
                saleToSave.setAmount(amount);
                saleToSave.setFinalPrice(ticket.getPrice() * amount);
                saleToSave.setUser(user);
                saleSaved = sd.registerSale(saleToSave);

                if(saleSaved != null){
                    session.setAttribute("email",ticket.getUser().getEmail());
                    session.setAttribute("loginOn",true);
                    session.setAttribute("ticketBuy",true);
                    response.sendRedirect("userPanel");
                }else{
                    request.setAttribute("buyError",true);
                    rs = request.getRequestDispatcher("index.jsp");
                    rs.forward(request,response);
                }
            }else{
                request.setAttribute("noRegisterError",true);
                rs = request.getRequestDispatcher("index.jsp");
                rs.forward(request,response);
            }
        }
    }

    public void destroy() {
    }
}