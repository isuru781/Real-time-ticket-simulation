class Customer implements Runnable {
    private TicketPool ticketPool;
    private int customerRetrievalRate;
    private int quantity;

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) { //create a constructer
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    @Override
    public void run() {//thread is start this method running
        for (int i = 0; i < quantity; i++) {//check whether one customer ticket quantity exide
           ticketPool.buyTicket();//buy a ticket

            try {
                Thread.sleep(customerRetrievalRate * 1000);//thred sleep
            } catch (InterruptedException e) {//exeption handling
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}