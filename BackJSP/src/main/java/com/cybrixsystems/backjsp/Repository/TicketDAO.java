package com.cybrixsystems.backjsp.Repository;

import com.cybrixsystems.backjsp.Model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketDAO {
    List<Ticket> findAllTickets();
    List<Ticket> findAllTicketsByEmail(String email);
    Optional<Ticket> findTicketById(Long id);
    Optional<Ticket> findTicketByNum(int ticketNums);
    Ticket saveTicket(Ticket ticket);
    public Ticket butTicket(String category, int amount,String email);
    void deleteTicketById(Long idTicket);
}
