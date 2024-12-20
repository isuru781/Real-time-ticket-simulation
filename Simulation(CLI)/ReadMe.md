# Real-Time Event Ticketing System

## Overview

This Real-Time Event Ticketing System simulates the release and purchase of tickets in a concurrent environment. 
The system includes vendors who add tickets to a shared pool and customers who retrieve tickets. 
The ticketing simulation also includes a monitoring thread to observe the process and stop the simulation when all tickets are sold.
The system ensures thread safety and uses logging to provide detailed feedback on ticket transactions.

---

## Features

1. **Simulated Ticket Release and Purchase**

   - Vendors add tickets to a shared pool at a defined rate.
   - Customers retrieve tickets at their own rate.

2. **Concurrency Management**

   - Threads are used to simulate vendors and customers.
   - Synchronization ensures safe access to the ticket pool.

3. **Real-Time Monitoring**

   - A monitoring thread observes ticket sales and stops the simulation when all tickets are sold.

4. **Logging**

   - Uses Log4j for detailed event logging.

---

## Components

### 1. `TicketPool`

Manages ticket availability and sales.all ticket operations are handle this class

- **Key Methods**:
  - `addTicket(Ticket ticket)`: Adds a ticket to the pool.
  - `buyTicket()`: Allows customers to retrieve tickets.
  - `getAvailableTicketsCount()`: Returns the number of available tickets.
  - `getTicketsSold()`: Returns the number of tickets sold.

### 2. `Vendor`
this is use a add a ticket to pool.vendor thread that adds tickets to the pool define rate

### 3. `Customer`

Simulates a customer get tickets from the pool at a defined rate.

### 4. `Monitor`

check the simulation and stops it when all tickets are sold.

### 5. `SystemConfigaration`

 configuration of system, including inputs for total tickets, ticket release rates, customer retrieval rates,
  and maximum pool capacity. Also manages JSON-based storage and retrieval of configurations.

### 6. `Main`

The entry point of the system that manages user interaction and the simulation lifecycle.

---

## How to Use(CLI)

### Step 1: Configure the System

Run the program and enter configuration parameters:

- Total tickets: 50-500
- Ticket release rate: 1-5 (seconds per ticket addition)
- Customer retrieval rate: 1-5 (seconds per ticket retrieval)
- Maximum ticket pool capacity: 10-100

Optionally, save the configuration to a JSON file for future use.

### Step 2: Start the Simulation

Select `1` from the menu to start the simulation. The system will:

- Start vendor threads to add tickets.
- Start customer threads to retrieve tickets.
- Monitor the simulation until all tickets are sold.

### Step 3: View Status

Select `3` to view the current status of the simulation:

- Number of tickets available.
- Number of tickets sold.
- Number of tickets added.

### Step 4: Stop the Simulation

Select `2` to stop the simulation manually. This interrupts all active threads.

### Step 5: Exit the Program

Select `4` to stop the simulation (if running) and exit the program.


## JSON Data saving 

- Configurations are saved to `ticketDataList.json`.
- The file stores:
  - Total tickets
  - Ticket release rate
  - Customer retrieval rate
  - Maximum ticket pool capacity
- The system loads existing configurations at startup, if available.


## Logging

- All events are logged using Log4j.
- Logs include:
  - Ticket additions and purchases.
  - Thread actions (start, stop, wait, interrupt).
  - Simulation status updates.


## Prerequisites

1. **Java Development Kit (JDK)**

   - Ensure JDK 8 or higher is installed.

2. **Log4j**

   - Include the Log4j library in your project dependencies.

3. **Gson**

   - Ensure the Gson library is available for JSON handling.

## Authors

- Your Name - [Isuru marasinghe](https://github.com/isuru781)
