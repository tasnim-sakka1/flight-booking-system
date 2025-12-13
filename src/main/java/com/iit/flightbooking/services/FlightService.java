package com.iit.flightbooking.services;

import com.iit.flightbooking.entities.Flight;

import java.util.List;

public interface FlightService {

    public Flight save(Flight flight);

    public List<Flight> findAll();

    public Flight findById(Long id);

    public Flight update(Long id, Flight flight);

    public Flight delay(Long id); // mark DELAYED (no minutes)

    public Flight delay(Long id, Integer minutes); // mark DELAYED + shift times

    public Flight cancel(Long id);

    public void deleteById(Long id);
}
