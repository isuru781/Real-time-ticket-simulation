package com.session02.newpos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Entity

@Table(name="configTicket")//create a ticket configuration database
@Service

public class ConfigTicket {

    @Id
    @Column(name="Configaration_ID")//ticket id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ConfigarationID;

    @Column(name = "total_ticket ")//total tickets
    private int totalTicket;

    @Column(name="custemerBuyingrate")//custemr buying rate
    private int custemerBuyingRate;

    @Column(name="VenderAddingRate")//create vender ticket adding rate
    private int VenderAddingRate;


    @Column(name = "MaxTicketCapacity")//get max ticket capacity
    private int MaxTicketCapacity;

    public ConfigTicket(int totalTicket, int custemerBuyingRate, int venderAddingRate, int maxTicketCapacity) {
        this.totalTicket = totalTicket;
        this.custemerBuyingRate = custemerBuyingRate;
        VenderAddingRate = venderAddingRate;
        MaxTicketCapacity = maxTicketCapacity;
    }

    public ConfigTicket() {

    }

    public int getTotalTicket() {
        return totalTicket;
    }

    public void setTotalTicket(int totalTicket) {
        this.totalTicket = totalTicket;
    }

    public int getCustemerBuyingRate() {
        return custemerBuyingRate;
    }

    public void setCustemerBuyingRate(int custemerBuyingRate) {
        this.custemerBuyingRate = custemerBuyingRate;
    }

    public int getVenderAddingRate() {
        return VenderAddingRate;
    }

    public void setVenderAddingRate(int venderAddingRate) {
        VenderAddingRate = venderAddingRate;
    }

    public int getMaxTicketCapacity() {
        return MaxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        MaxTicketCapacity = maxTicketCapacity;
    }
}

