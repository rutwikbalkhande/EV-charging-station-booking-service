package com.example.ev_station_service.dto;


import jakarta.validation.constraints.Min;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationResponseDTO {

    private Long id;
    private String name;
    private String location;

    @Min(value = 0 , message = "slot cannot be negative")
    private int availableSlots;
}