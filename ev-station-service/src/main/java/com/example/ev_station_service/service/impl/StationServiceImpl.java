package com.example.ev_station_service.service.impl;

import com.example.ev_station_service.dto.StationDTO;
import com.example.ev_station_service.dto.StationResponseDTO;
import com.example.ev_station_service.dto.StationUpdateDTO;
import com.example.ev_station_service.entity.Station;
import com.example.ev_station_service.mapper.StationMapper;
import com.example.ev_station_service.repository.StationRepository;
import com.example.ev_station_service.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository repository;

    @Autowired
    private StationMapper mapper;

    @Override
    public StationResponseDTO createStation(StationDTO dto) {
       Station station = mapper.toEntity(dto);

        return  mapper.toDTO(repository.save(station));
    }

    @Override
    public StationResponseDTO updateStation(Long id, StationDTO dto) {
         Station station= repository.findById(id).orElseThrow( ()->new RuntimeException("station not found"));
        mapper.updateEntityFromDto(dto, station);
        return mapper.toDTO(repository.save(station));
    }

    @Override
    public StationResponseDTO partialUpdateStation(Long id, StationUpdateDTO dto) {
        Station station = repository.findById(id).orElseThrow(()-> new RuntimeException("station not found with id: "+ id));
        mapper.updateEntityFromDto(dto, station);
        return mapper.toDTO(repository.save(station));
    }

    @Override
    public StationResponseDTO getStationById(Long id) {
        return repository.findById(id).map(mapper::toDTO)
                      .orElseThrow(()-> new RuntimeException("station not found!"));
    }

    @Override
    public Page<StationResponseDTO> getAllStations(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).map(mapper::toDTO);
    }

    @Override
    public List<StationResponseDTO> searchStations(String name, String location) {
        return repository.findByNameContainingAndLocationContaining(name, location)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public List<StationResponseDTO> filterStationsByAvailableSlots(int minSlots) {
        return repository.findByAvailableSlotsGreaterThan(minSlots)
                    .stream().map(mapper::toDTO).toList();

    }

    @Override
    public void deleteStation(Long id) {
        if(!repository.existsById(id)){
            throw new RuntimeException("id not found!");
        }
        else {
            repository.deleteById(id);
        }

    }

    @Override
    public List<StationResponseDTO> getAvailableStations() {
        return repository.findByAvailableSlotsGreaterThan(0)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}