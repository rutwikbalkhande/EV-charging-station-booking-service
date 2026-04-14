package com.example.ev_station_service.service;


import com.example.ev_station_service.dto.StationRequestDto;
import com.example.ev_station_service.dto.StationResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StationService {

 StationResponseDto addStation(StationRequestDto requestDto);
 List<StationResponseDto> getAllStations();
 StationResponseDto getStationById(Long id);
 StationResponseDto updateStation(Long id, StationRequestDto requestDto);
 void deleteStation(Long id);
 List<StationResponseDto> searchStations(String location);
 List<StationResponseDto> getAvailableStations();
 //List<StationResponseDto> filterByMinSlots(int minSlots);
 public Page<StationResponseDto> getStations(int page, int size);

 Page<StationResponseDto> getStations(int page, int size, String sortBy, String sortDirection);
}
