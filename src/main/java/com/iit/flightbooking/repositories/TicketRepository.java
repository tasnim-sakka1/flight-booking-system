package com.iit.flightbooking.repositories;

import com.iit.flightbooking.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
