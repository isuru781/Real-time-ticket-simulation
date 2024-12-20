package com.session02.newpos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer implements Runnable {
    private TicketPool ticketPool;
    private int customerRetrievalRate;
    private int quantity;


    public Customer() {
    }

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity && !Thread.currentThread().isInterrupted(); i++) {//check quntity and thred is intrupted
            try {
                Ticket ticket = ticketPool.buyTicket();//buy ticket
            if (ticket == null) break; // Graceful exit if null
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {//error handle
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
            System.err.println("Error occurred while buying ticket: " + e.getMessage());//other error handle
        }
        }
    }
}