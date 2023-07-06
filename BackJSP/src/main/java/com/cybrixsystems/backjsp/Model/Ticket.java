package com.cybrixsystems.backjsp.Model;


import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ticket {
    private Long id;
    private int ticketNum;
    private float price;
    private LocalDate eventDate;
    private User user;

    public Ticket(Long id,int ticketNum,float price,LocalDate eventDate){
        this.id = id;
        this.ticketNum = ticketNum;
        this.price = price;
        this.eventDate = eventDate;
    }
}
