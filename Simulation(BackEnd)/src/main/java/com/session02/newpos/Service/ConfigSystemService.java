package com.session02.newpos.Service;

import com.session02.newpos.Repo.ConfigSysDataRepo;
import com.session02.newpos.entities.ConfigTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigSystemService {//configartion data save

    @Autowired
    private ConfigSysDataRepo configSysDataRepo;

    public String saveConfigData(int totalTickets, int ticketReleaseRate,int customerRetrievalRate,int MaxTicketCapacity) {
        try {
            // Map DTO to Entity
            ConfigTicket configSystem = new ConfigTicket(//convert to configTicket data type
                    totalTickets,
                    ticketReleaseRate,
                    customerRetrievalRate,
                    MaxTicketCapacity
            );
            configSysDataRepo.save(configSystem);//save data base
            return "Successfully configuration saved to database";

        } catch (Exception e) {//error handling
            e.printStackTrace();
            return "Failed to save configuration: " + e.getMessage();
        }
    }
}