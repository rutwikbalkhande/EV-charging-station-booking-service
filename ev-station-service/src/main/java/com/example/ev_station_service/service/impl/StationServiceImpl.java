package com.example.ev_station_service.service.impl;

import com.example.ev_station_service.annotation.AuditAction;
import com.example.ev_station_service.annotation.CheckRole;
import com.example.ev_station_service.annotation.LogExecutionTime;
import com.example.ev_station_service.dto.StationRequestDto;
import com.example.ev_station_service.dto.StationResponseDto;
import com.example.ev_station_service.entity.Station;
import com.example.ev_station_service.mapper.StationMapper;
import com.example.ev_station_service.repository.StationRepository;
import com.example.ev_station_service.service.StationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository repository;
    private final StationMapper mapper;

    @Override
    @Transactional
    @AuditAction(action="create")
    public StationResponseDto addStation(StationRequestDto requestDto) {
        Station station= mapper.toEntity(requestDto);
        if(station.getCapacity()< requestDto.getOccupiedSlots()){
            throw new RuntimeException("not book more than capacity?");
        }
        return mapper.toResponseDto(repository.save(station));
    }

    @Override
    @LogExecutionTime
    public List<StationResponseDto> getAllStations() {

        return  repository.findAll()
                .stream().map(mapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    @LogExecutionTime
    public StationResponseDto getStationById(Long id) {
       Station station= repository.findById(id).orElseThrow(()->new RuntimeException("station not found with id: "+ id));
        return mapper.toResponseDto(station);
    }



    @Override
    @Transactional
    @AuditAction(action="update")
    public StationResponseDto updateStation(Long id, StationRequestDto requestDto) {
        Station existing = repository.findById(id)
                                    .orElseThrow(() -> new RuntimeException("Station not found with id: " + id));
        mapper.updateEntityFromDto(requestDto, existing);   // ✅ MapStruct handles partial update
        return mapper.toResponseDto(repository.save(existing));
    }

    @Override
    @Transactional
    @AuditAction(action="delete")
    @CheckRole("ADMIN")
    public void deleteStation(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<StationResponseDto> searchStations(String location) {
        return repository.findByLocationContainingIgnoreCase(location)
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StationResponseDto> getAvailableStations() {
        return repository.findAll().stream()
                .filter(station -> station.getAvailableSlots() > 0)
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public Page<StationResponseDto> getStations(int page, int size) {
        Page<Station> stations = repository.findAll(PageRequest.of(page, size));
        return stations.map(mapper::toResponseDto);
    }

    @Override
    public Page<StationResponseDto> getStations(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Page<Station> stations = repository.findAll(PageRequest.of(page, size, sort));
        return stations.map(mapper::toResponseDto);
    }
}