package com.example.ev_station_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DialectOverride;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationRequestDto {

    @NotBlank(message="station name required")
    private String stationName;

    @NotBlank(message="location required")
    private String location;

    @NotNull(message="capacity required")
    @Positive(message = "capacity must greater than zero")
    private Integer capacity;

    @NotNull(message = "Price per KWh is required")
    @Positive(message = "Price per KWh must be greater than zero")
    private Double pricePerKWh;

    @NotNull(message = "Availability flag is required")
    private Integer occupiedSlots;  // client sends how many slots are occupied

    private int availableSlots;
}