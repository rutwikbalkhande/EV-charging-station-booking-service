package com.example.BookingService.service.impl;

import com.example.BookingService.annotation.AuditAction;
import com.example.BookingService.annotation.LogExecutionTime;
import com.example.BookingService.client.StationClient;
import com.example.BookingService.dto.BookingRequestDTO;
import com.example.BookingService.dto.BookingResponseDTO;
import com.example.BookingService.dto.StationDTO;
import com.example.BookingService.dtomapper.BookingMapper;
import com.example.BookingService.entity.Booking;
import com.example.BookingService.entity.BookingStatus;
import com.example.BookingService.entity.TimeSlot;
import com.example.BookingService.repository.BookingRepository;
import com.example.BookingService.repository.TimeSlotRepository;
import com.example.BookingService.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepo;
    private final TimeSlotRepository slotRepo;
    private final BookingMapper mapper;
    private final StationClient stationClient;

    // 🔥 BOOK SLOT (TRANSACTION + LOCK)
    @Override
    @AuditAction(action = "create")
    @Transactional
    public BookingResponseDTO bookSlot(BookingRequestDTO dto) {

        // 1. Validate station via Feign
        StationDTO station = stationClient.getStation(dto.getStationId());
        if (station == null) {
            throw new RuntimeException("Station not found");
        }

        // 2. Lock slot row
        TimeSlot slot = slotRepo.findByIdForUpdate(dto.getTimeSlotId());
        if (slot == null) {
            throw new RuntimeException("Slot not found");
        }

        // 3. Validate mapping
        if (!slot.getStationId().equals(dto.getStationId())) {
            throw new RuntimeException("Invalid station-slot mapping");
        }

        // 4. Validate request
        if (dto.getSlotsBooked() <= 0) {
            throw new RuntimeException("Invalid slot count");
        }

        // 5. Check availability
        if (slot.getAvailableSlots() < dto.getSlotsBooked()) {
            throw new RuntimeException("Not enough slots available");
        }

        // 6. Reduce slots (SAFE)
        slot.setAvailableSlots(
                slot.getAvailableSlots() - dto.getSlotsBooked()
        );

        // 7. Save booking
        Booking booking = mapper.toEntity(dto);
        booking.setStatus(BookingStatus.BOOKED);

        Booking saved = bookingRepo.save(booking);

        return mapper.toDTO(saved);
    }

    // 🔥 CANCEL BOOKING
    @Override
    @AuditAction(action ="cancle")
    @Transactional
    public BookingResponseDTO cancelBooking(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Already cancelled");
        }

        // Lock slot
        TimeSlot slot = slotRepo.findByIdForUpdate(booking.getTimeSlotId());

        slot.setAvailableSlots(
                slot.getAvailableSlots() + booking.getSlotsBooked()
        );

        booking.setStatus(BookingStatus.CANCELLED);

        return mapper.toDTO(booking);
    }

    @Override
    @LogExecutionTime
    public BookingResponseDTO getById(Long id) {
        return mapper.toDTO(
                bookingRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Not found"))
        );
    }

    @Override
    @LogExecutionTime
    public List<BookingResponseDTO> getAll() {
        return bookingRepo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    }
