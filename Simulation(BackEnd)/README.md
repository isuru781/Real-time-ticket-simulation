#  Java-based application that simulates a ticket purchasing system.


# The application uses Spring Boot and Maven for dependency management and project structure. The simulation involves vendors adding tickets to a pool and customers buying tickets from the pool.



## Features

- Simulate ticket vending and purchasing
- Real-time status updates
- Thread management for vendors and customers
- Graceful start and stop of the simulation

## Requirements

- Java 17
- Maven 3.6+
- MySQL database

## Setup

1. **open project File**
    open a spring boot project file 

2. **Configure the database:**
   Update the `application.properties` file with your MySQL database configuration.

3. **Build the project:**

   mvn clean install

4. **Run the application:**

   mvn spring-boot:run


## Usage

### Starting the Simulation

To start the simulation, call the `startSimulation` method from the `SimulationService` class with the following parameters:

- `totalTickets`: Total number of tickets to be sold
- `ticketReleaseRate`: Rate at which vendors release tickets
- `customerRetrievalRate`: Rate at which customers retrieve tickets
- `MaxTicketCapacity`: Maximum capacity of the ticket pool

set a configaration data and save a database to configaration data

### Stopping the Simulation

To stop the simulation, call the `stopSimulation` method from the `SimulationService` class.

### Resetting the Simulation

To reset the simulation, call the `resetSimulation` method from the `SimulationService` class.

### Viewing Status

To view the current status of the simulation, call the `showStatus` method from the `SimulationService` class.

### Getting Real-Time Data

To get real-time data while the simulation is running, call the `getRealTimeData` method from the `SimulationService` class.

## Project Structure

- `SimulationService.java`: Main service class to manage the simulation.
- `TicketPool.java`: Manages the pool of tickets.
- `Ticket.java`: Represents a ticket.
- `Vender.java`: Vendor class to add tickets to the pool.
- `Customer.java`: Customer class to buy tickets from the ticket  pool.

## Dependencies

- Spring Boot
- Spring Data JPA
- MapStruct
- MySQL Connector
- SLF4J


## Authors

- Your Name - [Isuru marasinghe](https://github.com/isuru781)

