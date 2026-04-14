package com.example.ev_station_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stationName;
    private String location;        // city or address
    private Integer capacity;       // total charging slots
    private Double pricePerKWh;
    private int availableSlots;     // number of free slots

    private Integer occupiedSlots;  // how many slots are currently in use

    // Derived property
    public int getAvailableSlots() {
        return capacity - (occupiedSlots != null ? occupiedSlots : 0);
    }

}
