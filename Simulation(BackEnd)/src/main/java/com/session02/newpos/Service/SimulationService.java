package com.session02.newpos.Service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    private static Thread[] vendorThreads;
    private static Thread[] customerThreads;
    static boolean simulationRunning;
    static TicketPool ticketPool;
    static final Logger logInfo = LoggerFactory.getLogger(SimulationService.class);

    public String startSimulation(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int MaxTicketCapacity) {//start simulation method
        if (simulationRunning) {//check simulation is running
            throw new IllegalStateException("Simulation is already running.");
        }
        logInfo.info("Simulation is started");
        try {
            simulationRunning = true;
            ticketPool = new TicketPool(MaxTicketCapacity);
            System.out.println("Starting simulation...");

            //create vender thred
            int vendorCount = 4;
            vendorThreads = new Thread[vendorCount];
            for (int i = 0; i < vendorCount; i++) {
                Vender vender = new Vender(totalTickets / 4, ticketReleaseRate, ticketPool);
                vendorThreads[i] = new Thread(vender, "Vendor ID-" + i);
                vendorThreads[i].start();//start thred
            }


            //crate customer thread
            int customerCount = 7;
            customerThreads = new Thread[customerCount];
            for (int i = 0; i < customerCount; i++) {
                Customer customer = new Customer(ticketPool, customerRetrievalRate, 15);
                customerThreads[i] = new Thread(customer, "Customer ID-" + i);
                customerThreads[i].start();//custemer thread start
            }

            new Thread(() -> {
                while (simulationRunning) {
                    synchronized (SimulationService.class) {
                        if (ticketPool.getTicketsSold() >= totalTickets) {
                            System.out.println("All tickets have been sold. Stopping simulation...");
                            stopSimulation();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();//moniter thread use to check a states
            return "simulation successfully started";

        } catch (Exception e) {
            logInfo.error("Error starting simulation: ", e);
            return "Error starting simulation: " + e.getMessage();
        }
    }

    public String stopSimulation() {//stop simulation method
        if (!simulationRunning) {//check siulation is running
            System.out.println("Simulation is not running.");
            return "Simulation is not running.";
        }

        System.out.println("Stopping simulation...");
        logInfo.info("Stopping simulation...");
        try {
            simulationRunning = false;
            if (vendorThreads != null) {
                for (Thread vendorThread : vendorThreads) {
                    if (vendorThread != null && vendorThread.isAlive()) {//thread check
                        vendorThread.interrupt();//vender thred intruppt
                    }
                }
            }

            if (customerThreads != null) {
                for (Thread customerThread : customerThreads) {
                    if (customerThread != null && customerThread.isAlive()) {//check thread
                        customerThread.interrupt();//custemer thred intrupt
                    }
                }
            }
            synchronized (ticketPool) {
                ticketPool.notifyAll(); // Notify all waiting threads
            }

            String ShowStates = showStatus();//show stetes
            return "simulation stopped successful . this is a current states " + ShowStates;
        } catch (Exception e) {
            logInfo.error("Error stopping simulation: ", e);
            return "Error stopping simulation: " + e.getMessage();
        }
    }

    public String showStatus() {//show stetes method

        logInfo.info("current states is showing ");
        System.out.println("Current status:");
        System.out.println("Number of tickets sold: " + ticketPool.getTicketsSold());
        System.out.println("number of ticket Available : " + ticketPool.getAvailbleTicket());
        System.out.println("Number of ticket added :" + ticketPool.getTicketAdded());
        System.out.println("total ticket of pool :" + Vender.getTotalTickets() * 4);

        String states=String.format("""
                Number of tickets sold: %d
                number of ticket Available : %d
                Number of ticket added : %d
                total ticket of pool : %d
                """, ticketPool.getTicketsSold(),ticketPool.getAvailbleTicket(), ticketPool.getTicketAdded(), Vender.getTotalTickets() * 4);


        return states;
    }

    public String resetSimulation() {//reset simulation
        try {
            if (simulationRunning) {
                stopSimulation();
            }
            ticketPool.clear();//reset ticket pool,vendor thred,customer thred
            vendorThreads = null;
            customerThreads = null;
            System.out.println("simulation reset successfully");
            return "Simulation has been reset.";

        } catch (NullPointerException e) {
            logInfo.error("NullPointerException occurred: ", e);
            return "Error resetting simulation: " + e.getMessage();
        }
    }

    public String getRealTimeData() {//real time data get method
        try {
            if (simulationRunning) {
                return String.format("""
                Number of tickets sold: %d
                number of ticket Available : %d
                Number of ticket added : %d
                total ticket of pool : %d
                """, ticketPool.getTicketsSold(),
                        ticketPool.getAvailbleTicket(),
                        ticketPool.getTicketAdded(),
                        Vender.getTotalTickets() * 4);
            }else{
                return "the real time data is coming only while the simulation is running";
            }
        } catch (NullPointerException e) {
            logInfo.error("NullPointerException occurred: ", e);
            return "Error resetting simulation: " + e.getMessage();
        }
    }
}