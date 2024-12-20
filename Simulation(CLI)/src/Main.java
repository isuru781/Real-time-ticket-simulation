
import java.util.Scanner;


public class Main {

    static TicketPool ticketPool;
    private static Thread[] vendorThreads;
    private static Thread[] customerThreads;
    static boolean simulationRunning;


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);


        System.out.println("***************************************************************");
        System.out.println("**   Welcome to the Real-Time Ticketing simulation System!   **");
        System.out.println("****************************************************************");

        SystemConfigaration config = new SystemConfigaration();//create a object to get inputs
        config.getInput(scanner);//get inputs

        // Initialize the ticket pool
        ticketPool = new TicketPool(config.getMaxTicketCapacity());//initial maximum ticket capacity

        simulationRunning = false;//give the default value to simulation is running
        boolean running = true;

        while (running) {//get inputs to run programs
            String[] commands = {
                    "1: Start the simulation",
                    "2: Stop the simulation",
                    "3: View current status",
                    "4: Exit the system"
            };

            System.out.println("Commands:");
            for (String command : commands) {
                System.out.println("- " + command);
            }
            System.out.println();
            System.out.print("> ");
            String command = scanner.next();

            switch (command) {//swich case using to get menu
                case "1":
                    startSimulation(config.getTotalTickets(), config.getTicketRleseRate(), config.getCustemerRetrivalRate());
                    break;
                case "2":
                    stopSimulation();//stop simulation manually
                    break;
                case "3":
                    showStatus();
                    break;
                case "4":
                    stopSimulation();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
        scanner.close();
        System.out.println("======================================================");
        System.out.println("        Thank you for using the ticketing system!      ");
        System.out.println("=======================================================");
    }

    //start simulation method
    private static void startSimulation(int totalTickets, int ticketReleaseRate, int customerRetrievalRate) {
        if (simulationRunning) {//check simulation is running
            TicketPool.logger.info("Simulation is already running.");
            return;
        }
        //not running start simulation

        TicketPool.logger.info("Starting simulation...");
        simulationRunning = true;

        int vendorCount = 10;//vender count is hard coded

        //create a vender thread
        vendorThreads = new Thread[vendorCount];
        for (int i = 0; i < vendorCount; i++) {
            Vendor vendor=new Vendor(totalTickets/10, ticketReleaseRate, ticketPool);//vendor object  data initialization
            vendorThreads[i] = new Thread(vendor, "Vendor ID-" + i);
            vendorThreads[i].start();//thred start
        }

        int customerCount = 7;

        //create a costumer thread
        customerThreads = new Thread[customerCount];
        for (int i = 0; i < customerCount; i++) {
            Customer customer=new Customer(ticketPool, customerRetrievalRate, 10);//initial data to customer
            customerThreads[i] = new Thread(customer, "Customer ID-" + i);
            customerThreads[i].start();//thread start
        }
        //create a monitor thread
        Monitor monitor = new Monitor(totalTickets);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();//thread start
    }

//stop simulation function
    public static void stopSimulation() {
        if (!simulationRunning) {//check simulation is running
            TicketPool.logger.info("Simulation is not running.");
            return;
        }
        TicketPool.logger.info("Stopping simulation...");
        showStatus();//Display current states of simulation

        simulationRunning = false;//simulation running to false

        if (vendorThreads != null) {//check vendor thread is not null
            for (Thread vendorThread : vendorThreads) {
                if (vendorThread != null && vendorThread.isAlive()) {//check vendor thread is not null and thread is running
                    vendorThread.interrupt(); //interrupt vendor thread
                }
            }
        }

        if (customerThreads != null) {//check customer thread is not null
            for (Thread customerThread : customerThreads) {
                if (customerThread != null && customerThread.isAlive()) {//check custemer thred is nut null and thread is running
                    customerThread.interrupt(); // intrupt custemer thread
                }
            }
        }
    }


    private static void showStatus() {//show a ticket stetes
        System.out.println("Current status:");
        System.out.println("Tickets available: " + ticketPool.getAvailableTicketsCount());
        System.out.println("Number of ticket Tickets sold: " + ticketPool.getTicketsSold());
        System.out.println("Number of ticket added :"+ticketPool.getTicketAdded());
    }
}