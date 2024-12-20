import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Vector;


class TicketPool {
    private int maxCapacity;
    private int ticketsSold;
    private int ticketAdded;
    private Vector<Ticket> availableTickets;
  public static   Logger logger=Logger.getLogger(TicketPool.class);//to threow log data

    public TicketPool(int maxCapacity){//constructer
        this.maxCapacity = maxCapacity;
        this.availableTickets = new Vector<>();
        this.ticketsSold = 0;
        this.ticketAdded = 0;
    }

    public synchronized void addTicket(Ticket ticket) {//add ticket method
        while (availableTickets.size() >= maxCapacity) {//check max capacity reched in pool
            try {
                logger.info("maximum ticket capacity reached.wait to customers bought ticket");
                wait();// wait to customur buy ticket
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        ticketAdded++;//add a added ticket in pool
        availableTickets.add(ticket);//add a ticket to available ticket list
        notifyAll();//notify to other threads
        logger.info("Ticket added by - " + Thread.currentThread().getName() + " - current Available ticket count is - " + availableTickets.size());
    }

    public synchronized Ticket buyTicket() {//method to buy ticket
        while (availableTickets.isEmpty()) {//check avaailable tickets are empty
            if (!Thread.currentThread().isInterrupted()) {//check the ticket is  not intrupted
                try {
                    System.out.println("available tickets are empty. wait to vendor add ticket");
                    logger.info("available tickets are empty. wait to vendor add ticket");
                    wait();//wait for adding ticket
                } catch (InterruptedException e){//exeption handling
                    Thread.currentThread().interrupt();
                    return null;
                }
            } else {
                return null;
            }
        }
        ticketsSold++;//add a ticket sold
        Ticket ticket = availableTickets.remove(0);//remove ticket from available list
        notifyAll();//notify to other threads
        logger.info("Ticket bought by - " + Thread.currentThread().getName() + " - current Available ticket count is - " + availableTickets.size() + " - Ticket is - " + ticket);
        return ticket; //return a ticket
    }

   //use to get available ticket sizze
    public synchronized int getAvailableTicketsCount() {
        return availableTickets.size();
    }

    //use to get sold ticket size
    public synchronized int getTicketsSold() {
        return ticketsSold;
    }

    public int getTicketAdded() {
        return ticketAdded;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

}