package com.example.BookingService.dtomapper;


import com.example.BookingService.dto.BookingRequestDTO;
import com.example.BookingService.dto.BookingResponseDTO;
import com.example.BookingService.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking toEntity(BookingRequestDTO dto);

    BookingResponseDTO toDTO(Booking booking);
}