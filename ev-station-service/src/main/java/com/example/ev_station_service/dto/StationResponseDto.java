package com.example.ev_station_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationResponseDto {
    private Long id;
    private String stationName;
    private String location;
    private Integer capacity;
    private Double pricePerKWh;

    private Integer occupiedSlots;

    private int availableSlots;

}