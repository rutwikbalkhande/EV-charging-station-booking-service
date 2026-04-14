package com.example.ev_station_service.repository;

import com.example.ev_station_service.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StationRepository extends JpaRepository <Station, Long> {

    List<Station> findByLocationContainingIgnoreCase(String location);
    List<Station> findByAvailableSlotsGreaterThanEqual(int slots);
}

/*
# findByNameContainingAndLocationContaining =>

  1. this method generate automatically query JPQL " give proper method name thats why internally generated.

  2. @Query =>
        * annotation use if method name not proper OR  provide custom then use @Query( " " ) and run custom query for that method.
      Ex:
  1. using JPQL in @Query("")=>

     # Manually query bez wrong method name

        @Query("SELECT s FROM Station s WHERE s.availableSlots > :slots")
        List<Station> AvailableSlotsMoreThan(int slots);         // not generate automatically use @Query

   2. Use SQL >
           @Query(value = "SELECT * FROM station WHERE available_slots > :slots", nativeQuery = true)
           List<Station> getStations(@Param("slots") int slots);

 # Auto Generate JPQL use proper method name like

     * findByAvailableSlotsGreaterThan(int slots);
     *  List<Station> findByNameContainingAndLocationContaining(String name, String location);
}

 */
