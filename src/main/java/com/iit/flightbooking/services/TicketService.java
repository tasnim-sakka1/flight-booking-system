package com.iit.flightbooking.services;

import com.iit.flightbooking.entities.Ticket;

import java.util.List;

public interface TicketService {

    public Ticket save(Ticket ticket, Long travelerId, Long flightId);

    public List<Ticket> findAll();

    public Ticket findById(Long id);

    public Ticket update(Long id, Ticket ticket, Long travelerId, Long flightId);

    public Ticket activate(Long id);

    public Ticket cancel(Long id);

    public void deleteById(Long id);
}
