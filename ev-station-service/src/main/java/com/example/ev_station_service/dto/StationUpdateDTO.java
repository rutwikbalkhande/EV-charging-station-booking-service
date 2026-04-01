package com.example.ev_station_service.dto;

import lombok.Data;

@Data
public class StationUpdateDTO {

    private String name;
    private String location;
    private Integer availableSlots; // Use Integer to allow null values
}
