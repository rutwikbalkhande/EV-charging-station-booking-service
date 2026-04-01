package com.example.ev_station_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class EvStationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvStationServiceApplication.class, args);
		System.out.println("started ev-station service........");

	}

}
