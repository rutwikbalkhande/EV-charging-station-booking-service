package com.example.BookingService.dto;

import lombok.Data;

@Data
public class BookingResponseDTO {
    private Long id;
    private Long stationId;
    private Long timeSlotId;
    private String userName;
    private int slotsBooked;
    private String status;
}