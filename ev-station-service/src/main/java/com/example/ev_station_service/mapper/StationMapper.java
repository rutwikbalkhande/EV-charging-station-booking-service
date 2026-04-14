package com.example.ev_station_service.mapper;


import com.example.ev_station_service.dto.StationRequestDto;
import com.example.ev_station_service.dto.StationResponseDto;
import com.example.ev_station_service.entity.Station;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StationMapper {

    Station toEntity(StationRequestDto dto);

    StationResponseDto toResponseDto(Station entity);

    // ✅ Partial update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(StationRequestDto dto, @MappingTarget Station entity);
}