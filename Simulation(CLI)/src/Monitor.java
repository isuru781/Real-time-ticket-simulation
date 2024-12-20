public class Monitor extends Vendor implements Runnable {

    public Monitor(int totalTickets) {// to get total ticket
        super(totalTickets);

    }

    @Override
    public void run() {//when thread starts this method is running
        while (Main.simulationRunning) {//check simulation is running
            if (Main.ticketPool.getTicketsSold() >= getTotalTickets()) {//check total number of ticket get by customers
                TicketPool.logger.info("All tickets have been sold. Stopping simulation...");
                Main.stopSimulation();//stop simulation total ticket buy custemers
                break;//exit loop
            }
            try {
                Thread.sleep(1000);//Sleep thread in 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // when the thread is interrupted.Restore a thread interrupt states
                break;//exit loop
            }
        }
    }
}