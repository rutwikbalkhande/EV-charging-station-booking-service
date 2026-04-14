package com.example.BookingService.dto;

import lombok.Data;

@Data
public class StationDTO {
    private Long id;
    private String name;
    private String location;
    private int totalSlots;
    private int totalSlot;
    private int availableSlots;
}