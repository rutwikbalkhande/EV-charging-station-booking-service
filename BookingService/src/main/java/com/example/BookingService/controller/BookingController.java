package com.example.BookingService.controller;

import com.example.BookingService.dto.BookingRequestDTO;
import com.example.BookingService.dto.BookingResponseDTO;
import com.example.BookingService.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping
    public BookingResponseDTO book(@RequestBody BookingRequestDTO dto) {
        return service.bookSlot(dto);
    }

    @PutMapping("/{id}/cancel")
    public BookingResponseDTO cancel(@PathVariable Long id) {
        return service.cancelBooking(id);
    }

    @GetMapping("/{id}")
    public BookingResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<BookingResponseDTO> getAll() {
        return service.getAll();
    }
}