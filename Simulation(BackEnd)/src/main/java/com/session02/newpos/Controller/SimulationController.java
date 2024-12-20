package com.session02.newpos.Controller;

import com.session02.newpos.Service.ConfigSystemService;
import com.session02.newpos.Service.SimulationService;
import com.session02.newpos.Standerdresponses.StandResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simulation")
public class SimulationController {//use to get and sent data to front end

    @Autowired
    SimulationService simulationService;//create a object

    @Autowired
    ConfigSystemService configSystemService;//create a object


    @PostMapping(path="/start",
            params = {"totalTickets",
                    "ticketReleaseRate",
                    "customerRetrievalRate",
                    "MaxTicketCapacity"}
    )//start controller
    public ResponseEntity<StandResponses> startSimulation(@RequestParam(value = "totalTickets") int totalTickets,
                                                          @RequestParam(value = "ticketReleaseRate") int ticketReleaseRate,
                                                          @RequestParam(value = "customerRetrievalRate") int customerRetrievalRate,
                                                          @RequestParam(value = "MaxTicketCapacity") int MaxTicketCapacity
    ) {
        try {
            configSystemService.saveConfigData(totalTickets, ticketReleaseRate, customerRetrievalRate, MaxTicketCapacity);//save data to data base method
            String startSim = simulationService.startSimulation(totalTickets, ticketReleaseRate, customerRetrievalRate, MaxTicketCapacity);//start simulation
            ResponseEntity<StandResponses> respons = new ResponseEntity<StandResponses>(
                    new StandResponses(200, "Simulation started,configuration save to Data base ",startSim), HttpStatus.CREATED
            );//get standerd response

            return respons;
        } catch (IllegalStateException e) {//error handling
            String startSim="simulation Already running";
            ResponseEntity<StandResponses> respons = new ResponseEntity<StandResponses>(
                    new StandResponses(409, "Simulation Already started ",startSim), HttpStatus.CREATED
            );
            return respons;
        }


    }

    @PostMapping(path="/stop")//stop simulation controller
    public ResponseEntity<StandResponses> stopSimulation() {
        String stopsim=simulationService.stopSimulation();//call service class method
        ResponseEntity<StandResponses> stoping = new ResponseEntity<StandResponses>(
                new StandResponses(200, "\nSimulation stopped. You can show current states",stopsim), HttpStatus.CREATED
        );//standerd response
        return stoping;
    }

    @GetMapping("/status")
    public ResponseEntity<StandResponses> showStatus() {//show states controller
        String showstates=simulationService.showStatus();//call service class
        ResponseEntity<StandResponses> showstetess = new ResponseEntity<StandResponses>(
                new StandResponses(200, "you can show current states of ticket",showstates), HttpStatus.CREATED
        );//standerd response
        return showstetess;
    }
    @PostMapping("/reset")
    public ResponseEntity<StandResponses> resetSimulation() {//reset simulation controller
        try {
            String resetSim = simulationService.resetSimulation();//call srvice class method
            return new ResponseEntity<>(new StandResponses(200, "Simulation reset successfully.", resetSim), HttpStatus.OK);//return standard response
        } catch (NullPointerException e) {
            return new ResponseEntity<>(new StandResponses(500, "Error resetting simulation: " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/realtime")
    public ResponseEntity<StandResponses> getRealTimeData() {//send to real time data to front end
        String realTimeData = simulationService.getRealTimeData();//get real time stetes data
        ResponseEntity<StandResponses> response = new ResponseEntity<>(
                new StandResponses(200, "Real-time data", realTimeData), HttpStatus.OK
        );//standerd responses
        return response;
    }
}
