package com.example.ev_station_service.controller;

import com.example.ev_station_service.dto.StationDTO;
import com.example.ev_station_service.dto.StationResponseDTO;
import com.example.ev_station_service.dto.StationUpdateDTO;
import com.example.ev_station_service.service.StationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService service;

    @PostMapping()
    public ResponseEntity<StationResponseDTO> create( @Valid @RequestBody StationDTO dto) {
        return ResponseEntity.ok(service.createStation(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<StationResponseDTO> update(
            @PathVariable Long id,
            @RequestBody StationDTO dto) {
        return ResponseEntity.ok(service.updateStation(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StationResponseDTO> partialUpdate(
            @PathVariable Long id,
            @RequestBody StationUpdateDTO dto) {
        return ResponseEntity.ok(service.partialUpdateStation(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStationById(id));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<StationResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAllStations(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<List<StationResponseDTO>> search(
            @RequestParam String name,
            @RequestParam String location) {
        return ResponseEntity.ok(service.searchStations(name, location));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<StationResponseDTO>> filter(@RequestParam int minSlots) {
        return ResponseEntity.ok(service.filterStationsByAvailableSlots(minSlots));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteStation(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @GetMapping("/available")
    public ResponseEntity<List<StationResponseDTO>> getAvailable() {
        return ResponseEntity.ok(service.getAvailableStations());
    }
}