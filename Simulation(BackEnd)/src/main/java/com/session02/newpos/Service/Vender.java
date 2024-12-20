package com.session02.newpos.Service;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class Vender implements Runnable{
    @Getter
    private static int totalTickets;
    private int ticketReleaseRate;
    private TicketPool ticketPool;

    public Vender(int totalTickets, int ticketReleaseRate, TicketPool ticketPool) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
    }

    public Vender() {
    }

    @Override
    public void run() {//thread run method
        for (int i = 1; i <= totalTickets && !Thread.currentThread().isInterrupted(); i++) {//checktotal ticket and thred not intruptted
            Ticket ticket = new Ticket(i, "Simple Event", 1000);
            ticketPool.addTicket(ticket);//add ticket
            try {
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
            // Handle other exceptions
            System.err.println("Error occurred while buying ticket: " + e.getMessage());
        }
        }
    }
}