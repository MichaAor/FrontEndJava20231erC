package com.cybrixsystems.backjsp.Repository;

import com.cybrixsystems.backjsp.Config.DBConnection;
import com.cybrixsystems.backjsp.Model.Ticket;
import com.cybrixsystems.backjsp.Model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class TicketDAOIMP implements TicketDAO {
    private final Connection dbCon = DBConnection.getConnection();

    private final UserDAO ud = new UserDAOIMP();

    @Override
    public List<Ticket> findAllTickets() {
        List<Ticket> ticketsDB = new ArrayList<>();
        try {
            ResultSet rs = dbCon.prepareStatement("SELECT * FROM ticket").executeQuery();
            while(rs.next()){
                ticketsDB.add(
                        new Ticket(rs.getLong("id"),rs.getInt("ticketNum")
                                ,rs.getFloat("price")
                                ,rs.getDate("eventDate").toLocalDate()
                                ,ud.findUserById(rs.getLong("idUser")).orElseThrow()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketsDB;
    }

    @Override
    public List<Ticket> findAllTicketsByEmail(String userEmail) {
        List<Ticket> ticketsDB = new ArrayList<>();
        try {
            PreparedStatement ps = dbCon.prepareStatement(
                    "SELECT ticket.id,ticket.ticketNum,ticket.price,ticket.eventDate " +
                    "FROM ticket INNER JOIN user ON ticket.idUser = user.id WHERE user.email = ?");
            ps.setString(1,userEmail);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ticketsDB.add(
                        new Ticket(rs.getLong("id"),rs.getInt("ticketNum")
                                ,rs.getFloat("price")
                                ,rs.getDate("eventDate").toLocalDate())
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketsDB;
    }

    @Override
    public Optional<Ticket> findTicketById(Long id) {
        Optional<Ticket> ticketDB = Optional.empty();

        try{
            PreparedStatement ps = dbCon.prepareStatement("SELECT * FROM ticket WHERE id=?");
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ticketDB = Optional.of(
                        new Ticket(rs.getLong("id"),rs.getInt("ticketNum")
                                ,rs.getFloat("price")
                                ,rs.getDate("eventDate").toLocalDate()
                                ,ud.findUserById(rs.getLong("idUser")).orElseThrow()
                        ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ticketDB;
    }

    @Override
    public Optional<Ticket> findTicketByNum(int ticketNum) {
        Optional<Ticket> ticketDB = Optional.empty();

        try{
            PreparedStatement ps = dbCon.prepareStatement("SELECT * FROM ticket WHERE ticketNum=?");
            ps.setInt(1,ticketNum);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ticketDB = Optional.of(
                        new Ticket(rs.getLong("id"),rs.getInt("ticketNum")
                                ,rs.getFloat("price")
                                ,rs.getDate("eventDate").toLocalDate()
                                ,ud.findUserById(rs.getLong("idUser")).orElseThrow()
                        ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ticketDB;
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {

        try(PreparedStatement ps = dbCon.prepareStatement(
                "INSERT INTO ticket (ticketNum,price,eventDate,idUser) " +
                        "VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS)) {

             ps.setInt(1,ticket.getTicketNum());
             ps.setFloat(2,ticket.getPrice());
             ps.setDate(3,Date.valueOf(ticket.getEventDate()));
             ps.setFloat(4,ticket.getUser().getId());
             int affectedRows = ps.executeUpdate();

             if(affectedRows != 0){
                 ResultSet generateKey = ps.getGeneratedKeys();
                     if(generateKey.next()){
                         ticket.setId(generateKey.getLong(1));
                     }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public Ticket butTicket(String category, int amount,String email){
        Ticket ticket = null;
        if(email != null && category != null){
            if(amount != 0){
            Optional<User> userOpt = ud.findUserByEmail(email);
            LocalDate eventD = this.genTicketDate();
            if(userOpt.isPresent()) {
                for (int i = 0; i < amount; i++) {
                    ticket = new Ticket();
                    ticket.setTicketNum(this.generateTicketNum());
                    ticket.setPrice(this.getPrice(category));
                    ticket.setUser(userOpt.orElseThrow());
                    ticket.setEventDate(eventD);
                    this.saveTicket(ticket);
                    }
                }
            }
        }
        return ticket;
    }

    private float getPrice(String category){
        switch (category){
            case "estudiante" :
                return 200f - (200f * 0.8f);

            case "trainee" :
                return 200f - (200f * 0.5f);

            case "junior" :
                return 200f - (200f * 0.15f);

            default:
                return 200f;
        }
    }

    @Override
    public void deleteTicketById(Long idTicket) {
        try{
            PreparedStatement ps = dbCon.prepareStatement("DELETE FROM ticket where id = ?");
            ps.setLong(1,idTicket);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Complement Functions

    private int generateTicketNum(){
        int ticketNum;
        do{
            ticketNum = (int) (Math.random() * 999999999 + 1);
        }while(this.findTicketByNum(ticketNum).isPresent());
    return ticketNum;
    }

    private LocalDate genTicketDate(){
        long startD = LocalDate.now().toEpochDay();
        long endD = LocalDate.of(2025,12,29).toEpochDay();

        long randomD = ThreadLocalRandom.current().nextLong(startD,endD +1);

    return LocalDate.ofEpochDay(randomD);
    }
}
