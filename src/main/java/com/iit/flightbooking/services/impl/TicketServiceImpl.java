package com.iit.flightbooking.services.impl;

import com.iit.flightbooking.entities.Flight;
import com.iit.flightbooking.entities.Ticket;
import com.iit.flightbooking.entities.Traveler;
import com.iit.flightbooking.enums.TicketStatus;
import com.iit.flightbooking.mappers.FlightMapper;
import com.iit.flightbooking.mappers.TravelerMapper;
import com.iit.flightbooking.repositories.TicketRepository;
import com.iit.flightbooking.services.FlightService;
import com.iit.flightbooking.services.TicketService;
import com.iit.flightbooking.services.TravelerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private TravelerService travelerService;

    @Autowired
    private FlightMapper flightMapper;

    @Autowired
    private TravelerMapper travelerMapper;

    //    @Override
//    public Ticket save(Ticket ticket, Long travelerId, Long flightId) {
//        Traveler foundTraveler = travelerService.findById(travelerId);
//        Flight foundFlight = flightService.findById(flightId);
//        ticket.setTraveler(foundTraveler);
//        ticket.setFlight(foundFlight);
//        ticket.setCreatedAt(LocalDateTime.now());
//        ticket.setTicketStatus(TicketStatus.ACTIVE);
//        return ticketRepository.save(ticket);
//    }
    @Override
    public Ticket save(Ticket ticket, Long travelerId, Long flightId) {
        Traveler traveler = travelerService.findById(travelerId);
        Flight flight = flightService.findById(flightId);

        ticket.setTraveler(traveler);
        ticket.setFlight(flight);

//        if (ticket.getCreatedAt() == null) {
            ticket.setCreatedAt(LocalDateTime.now());
//        }
        if (ticket.getTicketStatus() == null) {
            ticket.setTicketStatus(TicketStatus.ACTIVE);
        }
        return ticketRepository.save(ticket);
    }


    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));
    }

    @Override
    public Ticket update(Long id, Ticket ticket, Long travelerId, Long flightId) {
        Traveler foundTraveler = travelerService.findById(travelerId);
        Flight foundFlight = flightService.findById(flightId);
        Ticket foundTicket = findById(id);
        foundTicket.setTicketStatus(ticket.getTicketStatus());
        foundTicket.setPrice(ticket.getPrice());
        foundTicket.setTraveler(foundTraveler);
        foundTicket.setFlight(foundFlight);
        return ticketRepository.save(foundTicket);
    }

    @Override
    public Ticket activate(Long id) {
        Ticket ticket = findById(id);
        ticket.setTicketStatus(TicketStatus.ACTIVE);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket cancel(Long id) {
        Ticket ticket = findById(id);
        ticket.setTicketStatus(TicketStatus.CANCELED);
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

}
