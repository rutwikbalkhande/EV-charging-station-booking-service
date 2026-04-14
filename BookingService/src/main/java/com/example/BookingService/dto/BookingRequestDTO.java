package com.example.BookingService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequestDTO {
    private Long stationId;
    private Long timeSlotId;
    private String userName;
    private int slotsBooked;
}