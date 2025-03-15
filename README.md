# Real-time-ticket-simulation

This Real-Time Event Ticketing System simulates the release and purchase of tickets in a concurrent environment.

## Features

- Simulate ticket vending and purchasing
- Real-time status updates
- Thread management for vendors and customers
- Graceful start and stop of the simulation

## Project Structure

### BackEnd

Java-based application that simulates a ticket purchasing system using Spring Boot and Maven for dependency management and project structure. The simulation involves vendors adding tickets to a pool and customers buying tickets from the pool.

#### Requirements

- Java 17
- Maven 3.6+
- MySQL database

#### Setup

1. **Open Project File:**
   Open a Spring Boot project file.

2. **Configure the database:**
   Update the `application.properties` file with your MySQL database configuration.

3. **Build the project:**

   ```sh
   mvn clean install
   ```

4. **Run the application:**

   ```sh
   mvn spring-boot:run
   ```

#### Usage

To start the simulation, call the `startSimulation` method from the `SimulationService` class with the following parameters:

- `totalTickets`: Total number of tickets to be sold
- `ticketReleaseRate`: Rate at which vendors release tickets
- `customerRetrievalRate`: Rate at which customers retrieve tickets
- `MaxTicketCapacity`: Maximum capacity of the ticket pool

Set configuration data and save to the database.

To stop the simulation, call the `stopSimulation` method from the `SimulationService` class.

To reset the simulation, call the `resetSimulation` method from the `SimulationService` class.

To view the current status of the simulation, call the `showStatus` method from the `SimulationService` class.

To get real-time data while the simulation is running, call the `getRealTimeData` method from the `SimulationService` class.

#### Dependencies

- Spring Boot
- Spring Data JPA
- MapStruct
- MySQL Connector
- SLF4J

### FrontEnd

React-based front-end application for a ticket simulation system. It allows users to start, stop, reset, and monitor the status of a ticket simulation.

#### Installation

1. Download the project file.

2. Navigate to the project directory:

    ```sh
    cd OOP_CW_Simulation_FrontEnd
    ```

3. Install the dependencies:

    ```sh
    npm install
    ```

#### Usage

To start the development server, run:

```sh
npm start
```

Open your browser and navigate to `http://localhost:3000` to view the application.

#### Components

1. `Home`
   - Located in [src/Pages/Home.js](src/Pages/Home.js). This component displays the welcome page and provides a link to start the simulation.

2. `SimulationPage`
   - Located in [src/Pages/SimulationPage.js](src/Pages/SimulationPage.js). This component contains the main simulation controls, including start, stop, reset, and status components.

3. `Start`
   - Located in [src/Component/Start.js](src/Component/Start.js). This component handles the form submission to start the simulation.

4. `Stop`
   - Located in [src/Component/Stop.js](src/Component/Stop.js). This component handles stopping the simulation.

5. `Status`
   - Located in [src/Component/Status.js](src/Component/Status.js). This component fetches and displays the current ticket status.

6. `Reset`
   - Located in [src/Component/Reset.js](src/Component/Reset.js). This component handles resetting the simulation.

7. `RealTimeConsoleLogger`
   - Located in [src/Component/RealTimeConsoleLogger.js](src/Component/RealTimeConsoleLogger.js). This component fetches and logs real-time data from the backend.

## Authors

- Your Name - [Isuru Marasinghe](https://github.com/isuru781)
