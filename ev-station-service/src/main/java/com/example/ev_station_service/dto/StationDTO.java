package com.example.ev_station_service.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StationDTO {

    private Long id;

    @NotBlank(message= "Name is Required")
    private String name;

    @NotBlank(message = "location is required")
    private String location;

    @NotNull(message="available slot is required")
    @Min(value = 0, message = "slots cannot be negative")
    private int availableSlots;
}
