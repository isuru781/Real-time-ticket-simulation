public class Vendor implements Runnable {
    private int totalTickets;
    private int ticketReleaseRate;
    private TicketPool ticketPool;

    public Vendor(int totalTickets, int ticketReleaseRate, TicketPool ticketPool) {//constructeer to get input
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
    }

    public Vendor(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    @Override
    public void run() {//thread is start this method running
        for (int i = 1; i <= totalTickets; i++) {//check total ticket of vender exicte
            if (Thread.currentThread().isInterrupted()) {//check a thred was intrupt
                break;
            }
            Ticket ticket = new Ticket(i, "Sparsha", 3500);//add a ticket
            ticketPool.addTicket(ticket);//add a ticket to availble list
            try {
                Thread.sleep(ticketReleaseRate * 1000);//thread sleep
            } catch (InterruptedException e) {//exeption handling
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

   // getter setter to access the data

     public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }
}