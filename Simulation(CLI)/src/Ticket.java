public class Ticket {
    private int ticketId;
    private String eventName;
    private double ticketPrice;

    public Ticket(int ticketId, String eventName, double ticketPrice) {//constructer
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {//to string method
        return "Ticket ID: " + ticketId + ", Event: " + eventName + ", Price: " + ticketPrice;
    }
}