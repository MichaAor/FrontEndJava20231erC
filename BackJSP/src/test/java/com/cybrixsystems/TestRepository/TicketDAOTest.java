package com.cybrixsystems.TestRepository;

import com.cybrixsystems.backjsp.Model.Ticket;
import com.cybrixsystems.backjsp.Model.User;
import com.cybrixsystems.backjsp.Repository.TicketDAO;
import com.cybrixsystems.backjsp.Repository.TicketDAOIMP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TicketDAOTest {
    private TicketDAO td;

    @BeforeEach
    void setUp(){
       this.td = new TicketDAOIMP();
    }

    @Test
    void findAllTickets_test(){
        List<Ticket> tickets = td.findAllTickets();

        assertFalse(tickets.isEmpty());
        tickets.forEach(System.out::println);
    }

    @Test
    void findAllTicketsByEmail_test(){
        String email = "mary@gmail.com";

        List<Ticket> tickets = td.findAllTicketsByEmail(email);

        assertFalse(tickets.isEmpty());
        tickets.forEach(System.out::println);
    }

    @Test
    void findTicketById_test(){
        Long idFind = 3L;

        Optional<Ticket> ticketOpt = td.findTicketById(idFind);

        assertTrue(ticketOpt.isPresent());
        assertEquals(idFind,ticketOpt.orElseThrow().getId());
    }

    @Test
    void saveTicket_test(){
        User user = new User(); user.setId(2L);
        Ticket ticketToSave = new Ticket(0L, (int) (Math.random() * 999999999 + 1)
                ,250f, LocalDate.of(2023,11,11), user);

        Ticket ticketSaved = td.saveTicket(ticketToSave);

        assertNotNull(ticketSaved);
        assertNotNull(ticketSaved.getId());
        assertEquals(ticketToSave.getPrice(),ticketSaved.getPrice());
        assertEquals(ticketToSave.getUser(),ticketSaved.getUser());
    }

    @Test
    void saveTicketGenNum_test(){
        User user = new User(); user.setId(3L);
        Ticket ticketToSave = new Ticket();
        ticketToSave.setPrice(250f);
        ticketToSave.setEventDate(LocalDate.of(2023,11,11));
        ticketToSave.setUser(user);

        Ticket ticketSaved = td.saveTicket(ticketToSave);

        assertNotNull(ticketSaved);
        assertNotNull(ticketSaved.getId());
        assertEquals(ticketToSave.getPrice(),ticketSaved.getPrice());
        assertEquals(ticketToSave.getUser(),ticketSaved.getUser());
    }

    @Test
    void deleteTicketById_test(){
        Long idDel = 2L;

        td.deleteTicketById(idDel);

        Optional<Ticket> ticketOpt = td.findTicketById(idDel);

        assertTrue(ticketOpt.isEmpty());
    }
}
