package com.iit.flightbooking.controllers;


import com.iit.flightbooking.dtos.FlightDto;
import com.iit.flightbooking.entities.Flight;
import com.iit.flightbooking.mappers.FlightMapper;
import com.iit.flightbooking.services.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Flight Controller
 *
 * @author Tasnim
 * @since 2025-11
 */
@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightMapper flightMapper;

    // CREATE
    /*
     * POST: http://localhost:8088/flights
     {
     "company": "Air France",
     "code": "AF123",
     "origin": "Paris",
     "destination": "New York",
     "departureTime": "2025-02-01T10:00:00",
     "arrivalTime": "2025-02-01T18:00:00",
     "totalSeats": 200,
     "flightStatus": "SCHEDULED"
     }
     */
    @PostMapping
    public ResponseEntity<FlightDto> create(@Valid @RequestBody FlightDto flightDto) {
        Flight flightEntity = flightMapper.toEntity(flightDto);
        Flight createdFlight = flightService.save(flightEntity);
        FlightDto createdFlightDto = flightMapper.toDto(createdFlight);
        return new ResponseEntity<>(createdFlightDto, HttpStatus.CREATED); // 201
    }

    // READ ALL
    // GET http://localhost:8088/flights
    @GetMapping
    public ResponseEntity<List<FlightDto>> getAll() {
        List<Flight> flights = flightService.findAll();
        List<FlightDto> flightDtos = flightMapper.toDto(flights);
        return ResponseEntity.ok(flightDtos); // 200
    }

    // READ ONE
    // GET http://localhost:8088/flights/1
    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getById(@PathVariable Long id) {
        Flight flight = flightService.findById(id);
        FlightDto flightDto = flightMapper.toDto(flight);
        return ResponseEntity.ok(flightDto); // 200
    }

    // UPDATE
    /*
     * PUT: http://localhost:8088/flights/1
     * {
     * "company": "Air France",
     * "code": "AF123",
     * "origin": "Paris",
     * "destination": "New York",
     * "departureTime": "2025-02-01T10:00:00",
     * "arrivalTime": "2025-02-01T18:00:00",
     * "totalSeats": 200,
     * "flightStatus": "SCHEDULED"
     * }
     */
    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> update(@PathVariable Long id, @Valid @RequestBody FlightDto flightDto) {
        Flight flightEntity = flightMapper.toEntity(flightDto);
        Flight updatedFlight = flightService.update(id, flightEntity);
        FlightDto updatedFlightDto = flightMapper.toDto(updatedFlight);
        return ResponseEntity.ok(updatedFlightDto); // 200
    }

    // DELAY FLIGHT
    // POST http://localhost:8088/flights/1/delay
    // POST http://localhost:8088/flights/1/delay?minutes=30
    @PostMapping("/{id}/delay")
    public ResponseEntity<FlightDto> delay(
            @PathVariable Long id,
            @RequestParam(required = false) Integer minutes) {

        Flight flight;
        if (minutes != null) {
            flight = flightService.delay(id, minutes);
        } else {
            flight = flightService.delay(id);
        }

        FlightDto flightDto = flightMapper.toDto(flight);
        return ResponseEntity.ok(flightDto); // 200
    }

    // CANCEL FLIGHT
    // POST http://localhost:8088/flights/1/cancel
    @PostMapping("/{id}/cancel")
    public ResponseEntity<FlightDto> cancel(@PathVariable Long id) {
        Flight flight = flightService.cancel(id);
        FlightDto flightDto = flightMapper.toDto(flight);
        return ResponseEntity.ok(flightDto); // 200
    }

    // DELETE
    // DELETE http://localhost:8081/flight-ms/flights/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        flightService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }
}
