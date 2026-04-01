package com.example.ev_station_service.service;

import com.example.ev_station_service.dto.StationDTO;
import com.example.ev_station_service.dto.StationResponseDTO;
import com.example.ev_station_service.dto.StationUpdateDTO;
import org.springframework.data.domain.Page;

import java.util.List;


public interface StationService {

 StationResponseDTO createStation(StationDTO dto);

 StationResponseDTO updateStation(Long id, StationDTO dto);

 StationResponseDTO partialUpdateStation(Long id, StationUpdateDTO dto);

 StationResponseDTO getStationById(Long id);

 Page<StationResponseDTO> getAllStations(int page, int size);

 List<StationResponseDTO> searchStations(String name, String location);

 List<StationResponseDTO> filterStationsByAvailableSlots(int minSlots);

 void deleteStation(Long id);

 List<StationResponseDTO> getAvailableStations();
}