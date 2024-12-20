import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SystemConfigaration {
    private int TotalTickets;
    private int ticketRleseRate;
    private int custemerRetrivalRate;
    private int MaxTicketCapacity;

    public void getInput(Scanner scanner) {


        System.out.println("==================================================================");
        System.out.println("       Ticket Simulation configuration System Configuration       ");
        System.out.println("===================================================================");
        while (true) {
            try {
                // Get and validate total ticket count
                while (true) {
                    System.out.print("Enter the total number of tickets (50-500): ");
                    TotalTickets = scanner.nextInt(); // Input get
                    if (TotalTickets >= 50 && TotalTickets <= 500) { // Validate
                        break;
                    } else { // Error message
                        System.out.println("Invalid input! Please enter a number between 50 and 500.");
                    }
                }

                // Get and validate ticket release rate
                while (true) {
                    System.out.print("Enter the ticket release rate (1-5): ");
                    ticketRleseRate = scanner.nextInt(); // Input get
                    if (ticketRleseRate >= 1 && ticketRleseRate <= 5) { // Validate
                        break;
                    } else { // Error message
                        System.out.println("Invalid input! Please enter a number between 1 and 5.");
                    }
                }

                // Get and validate customer retrieval rate
                while (true) {
                    System.out.print("Enter the customer retrieval rate (1-5): ");
                    custemerRetrivalRate = scanner.nextInt(); // Input get
                    if (custemerRetrivalRate >= 1 && custemerRetrivalRate <= 5) { // Validate
                        break;
                    } else { // Error message
                        System.out.println("Invalid input! Please enter a number between 1 and 5.");
                    }
                }

                // Get and validate maximum ticket capacity
                while (true) {
                    System.out.print("Enter the maximum ticket capacity (10-100): ");
                    MaxTicketCapacity = scanner.nextInt(); // Input get
                    if (MaxTicketCapacity >= 10 && MaxTicketCapacity <= 100) { // Validate
                        break;
                    } else { // Error message
                        System.out.println("Invalid input! Please enter a number between 10 and 100.");
                    }
                }

                // If all inputs are valid, exit the outer loop
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input from the scanner
            }
        }


        System.out.print("You want to save this configuration to json(y/n)  :");
        String save = scanner.next();
        while (true) {//check user input yes or not
            if (save.equalsIgnoreCase("y") ) {
                writeDataToJson();  //call a method to write a json  data
                System.out.println("your data is saved a json file :");
                break;
            } else if (save.equalsIgnoreCase("n")) {
                System.out.println("Your data is not save in json file : ");//not save to json
                break;
            } else {
                System.out.println("Invalid input. Please try again (y/n):");//invalid input
                save = scanner.nextLine();//again get input
            }
        }

        System.out.println();

        System.out.println("===========================================");
        System.out.println("       Your system is ready to use!        ");
        System.out.println("===========================================");
    }

    //load data from json
    public List<SystemConfigaration> loadDataFromJson() {
        List<SystemConfigaration> configurationList = new ArrayList<>();//create a array to store load data

        Gson gson = new Gson();//create a  gson


        try (Reader reader = new FileReader("ticketDataList.json")) {//read a json
            SystemConfigaration[] configArray = gson.fromJson(reader, SystemConfigaration[].class);//read data store

            // If the array is not null, convert it into a list
            if (configArray != null) {//check had load data
                for (SystemConfigaration config : configArray) {//inital data to Configration array
                    configurationList.add(config);
                }
            }
        } catch (IOException e) {
            // If the file doesn't exist or is empty, just return an empty list
            System.out.println("No previous data found. Starting fresh.");
        }

        return configurationList;//return a list
    }

    // metod create to write json
    public List<SystemConfigaration> writeDataToJson() {
        List<SystemConfigaration> configurationList = loadDataFromJson(); // load a preves data
        configurationList.add(this);//list ekata add kar

        Gson gson = new GsonBuilder().setPrettyPrinting().create();//create a gson  object


        try (FileWriter writer = new FileWriter("ticketDataList.json")) {//write to json file
            gson.toJson(configurationList, writer);//write kara
            System.out.println("List data written to ticketDataList.json successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configurationList;//return data list
    }


    //geter setter to acces data

    public int getTotalTickets() {
        return TotalTickets;
    }

    public int getTicketRleseRate() {
        return ticketRleseRate;
    }

    public int getCustemerRetrivalRate() {
        return custemerRetrivalRate;
    }

    public int getMaxTicketCapacity() {
        return MaxTicketCapacity;
    }
}
