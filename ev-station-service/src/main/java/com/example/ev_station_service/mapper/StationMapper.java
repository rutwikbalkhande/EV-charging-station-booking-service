package com.example.ev_station_service.mapper;

import com.example.ev_station_service.dto.StationDTO;
import com.example.ev_station_service.dto.StationResponseDTO;
import com.example.ev_station_service.dto.StationUpdateDTO;
import com.example.ev_station_service.entity.Station;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StationMapper {

    // CREATE
    Station toEntity(StationDTO dto);

    // RESPONSE
    StationResponseDTO toDTO(Station station);

    // FULL UPDATE (PUT)
    void updateEntityFromDto(StationDTO dto, @MappingTarget Station entity);

    // PARTIAL UPDATE (PATCH)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(StationUpdateDTO dto, @MappingTarget Station entity);
}