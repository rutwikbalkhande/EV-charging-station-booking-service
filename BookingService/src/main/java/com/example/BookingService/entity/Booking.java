package com.example.BookingService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stationId;
    private Long timeSlotId;

    private String userName;

    private int slotsBooked;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}