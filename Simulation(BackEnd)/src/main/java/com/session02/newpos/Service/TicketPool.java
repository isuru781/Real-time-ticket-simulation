package com.session02.newpos.Service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Vector;

import static com.session02.newpos.Service.SimulationService.logInfo;

@Component
public class TicketPool {
    private final int maxCapacity;

    @Getter
    private int ticketsSold;
    @Getter
    private int ticketAdded;

    private final Vector<Ticket> availableTickets = new Vector<>();

    public TicketPool(@Value("${ticketpool.maxCapacity:100}") int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(Ticket ticket) {//add ticket method
        while (availableTickets.size() >= maxCapacity) {
            try {
                logInfo.info("maximum ticket capacity reached.wait to customers bought ticket");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
//                System.err.println("Thread was interrupted while waiting 1: " + e.getMessage());
            }
        }

        ticketAdded++;
        availableTickets.add(ticket);
        notifyAll();
       logInfo.info("Ticket added by - " + Thread.currentThread().getName() + " - current Available ticket pool size is- " + availableTickets.size());
    }

    public synchronized Ticket buyTicket() {//buy method
        while (availableTickets.isEmpty() && SimulationService.simulationRunning) {//check avaailable tickets are empty and simulation is running check
            try {
                logInfo.info("Available tickets are empty. Waiting for vendor to add tickets...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null; // Return null to signal an interruption
            }
        }
        if (!SimulationService.simulationRunning) {
            return null; // Stop  if simulation ends
        }
        ticketsSold++;
        Ticket ticket = availableTickets.remove(0);
        notifyAll();
        logInfo.info("Ticket bought by " + Thread.currentThread().getName() + "-current Available ticket pool size is:- " + availableTickets.size());
        return ticket;
    }
    public synchronized void clear() {//clear a ticket pool
        availableTickets.clear();
        ticketsSold = 0;
        ticketAdded = 0;
    }


    public int getAvailbleTicket(){
        return  availableTickets.size();
    }


}
