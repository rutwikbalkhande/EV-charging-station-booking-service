package com.example.BookingService.client;

import com.example.BookingService.dto.StationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="STATION-SERVICE")
public interface StationClient {

    @GetMapping("api/station/id")
    StationDTO getStation(@PathVariable Long id);

}
