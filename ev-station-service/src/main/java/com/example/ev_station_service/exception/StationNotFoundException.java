package com.example.ev_station_service.exception;

public class StationNotFoundException extends RuntimeException{

    public StationNotFoundException(String message){

        super(message);
    }
}
