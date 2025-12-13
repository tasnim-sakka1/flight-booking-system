package com.iit.flightbooking.services.impl;


import com.iit.flightbooking.entities.Flight;
import com.iit.flightbooking.enums.FlightStatus;
import com.iit.flightbooking.repositories.FlightRepository;
import com.iit.flightbooking.services.FlightService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with id: " + id));
    }

    @Override
    public Flight update(Long id, Flight flight) {
        Flight foundFlight = findById(id);
        foundFlight.setCompany(flight.getCompany());
        foundFlight.setCode(flight.getCode());
        foundFlight.setOrigin(flight.getOrigin());
        foundFlight.setDestination(flight.getDestination());
        foundFlight.setDepartureTime(flight.getDepartureTime());
        foundFlight.setArrivalTime(flight.getArrivalTime());
        foundFlight.setTotalSeats(flight.getTotalSeats());
        foundFlight.setFlightStatus(flight.getFlightStatus());
        foundFlight.setDelayMinutes(flight.getDelayMinutes());
        foundFlight.setNewDepartureTime(flight.getNewDepartureTime());
        return save(foundFlight);
    }

    @Override
    public Flight delay(Long id) {
        Flight flight = findById(id);
        flight.setFlightStatus(FlightStatus.DELAYED);
        return flightRepository.save(flight);
    }

    @Override
    public Flight delay(Long id, Integer minutes) {
        Flight flight = findById(id);
        flight.setFlightStatus(FlightStatus.DELAYED);
        flight.setDelayMinutes(minutes);
        flight.setNewDepartureTime(flight.getDepartureTime().plusMinutes(minutes));
        return flightRepository.save(flight);
    }

    @Override
    public Flight cancel(Long id) {
        Flight flight = findById(id);
        flight.setFlightStatus(FlightStatus.CANCELED);
        // TODO :: change Ticket status to CANCELED
        // TODO :: Send notification email to Traveler the flight is canceled
        return flightRepository.save(flight);
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }
}
