package com.example.BookingService.service;

import com.example.BookingService.dto.BookingRequestDTO;
import com.example.BookingService.dto.BookingResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingService {

    BookingResponseDTO bookSlot(BookingRequestDTO dto);

    BookingResponseDTO cancelBooking(Long id);

    BookingResponseDTO getById(Long id);

    List<BookingResponseDTO> getAll();


}
