package com.example.ev_station_service.controller;

import com.example.ev_station_service.dto.StationRequestDto;
import com.example.ev_station_service.dto.StationResponseDto;

import com.example.ev_station_service.service.StationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationService service;


    @PostMapping("/save")
    public ResponseEntity<StationResponseDto> addStation(@RequestBody @Valid StationRequestDto requestDto) {
        return ResponseEntity.ok(service.addStation(requestDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<StationResponseDto>> getAllStations() {
        return ResponseEntity.ok(service.getAllStations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationResponseDto> getStationById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStationById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StationResponseDto> updateStation(@PathVariable Long id, @RequestBody  StationRequestDto requestDto) {
        return ResponseEntity.ok(service.updateStation(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        service.deleteStation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StationResponseDto>> searchStations(@RequestParam String location) {
        return ResponseEntity.ok(service.searchStations(location));
    }

    @GetMapping("/available")
    public ResponseEntity<List<StationResponseDto>> getAvailableStations() {
        return ResponseEntity.ok(service.getAvailableStations());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<StationResponseDto>> getStations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getStations(page, size));
    }

    @GetMapping("/page/sort")
    public ResponseEntity<Page<StationResponseDto>> getStations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue  = "10") int size,
            @RequestParam(defaultValue = "stationName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        return ResponseEntity.ok(service.getStations(page, size, sortBy, sortDirection));
    }
}
