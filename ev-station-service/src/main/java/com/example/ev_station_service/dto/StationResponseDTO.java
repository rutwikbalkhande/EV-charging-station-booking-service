package com.example.ev_station_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationResponseDTO {

    private Long id;
    private String name;
    private String location;
    private int availableSlots;
}