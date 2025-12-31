package com.iit.flightbooking.controllers;


import com.iit.flightbooking.entities.Ticket;
import com.iit.flightbooking.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    /**
     * Create a new ticket and publish Kafka event
     */
    //http://localhost:8088/api/tickets/1/3
/*    {
        "price": 350.00,
      } */
    @PostMapping("/{travelerId}/{flightId}")
    public ResponseEntity<Ticket> createTicket(
            @RequestBody Ticket ticket,
            @PathVariable Long travelerId,
            @PathVariable Long flightId) {

        Ticket created = ticketService.save(ticket, travelerId, flightId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Additional CRUD endpoints (optional)
     */

    @GetMapping
    public ResponseEntity<?> getAllTickets() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }


}

