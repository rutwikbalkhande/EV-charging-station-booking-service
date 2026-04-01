package com.example.ev_station_service.dto;


import lombok.Data;

@Data
public class StationDTO {


    private Long id;
    private String name;
    private String location;
    private int availableSlots;
}
