package com.iit.flightbooking;

import com.iit.flightbooking.entities.Flight;
import com.iit.flightbooking.entities.Ticket;
import com.iit.flightbooking.entities.Traveler;
import com.iit.flightbooking.enums.FlightStatus;
import com.iit.flightbooking.enums.TicketStatus;
import com.iit.flightbooking.repositories.FlightRepository;
import com.iit.flightbooking.repositories.TicketRepository;
import com.iit.flightbooking.repositories.TravelerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seedData(
            TravelerRepository travelerRepository,
            FlightRepository flightRepository,
            TicketRepository ticketRepository
    ) {
        return args -> {

            // --------- TRAVELERS ---------
            if (travelerRepository.count() == 0) {
                Traveler t1 = Traveler.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("john.doe1988.example@gmail.com") //password: johndoe123
                        .phone("12345678")
                        .passportNo("AA123456")
                        .address("New York, USA")
                        .build();

                Traveler t2 = Traveler.builder()
                        .firstName("Sara")
                        .lastName("Smith")
                        .email("elaoudnour2005@gmail.com")
                        .phone("87654321")
                        .passportNo("BB654321")
                        .address("Toronto, Canada")
                        .build();

                Traveler t3 = Traveler.builder()
                        .firstName("Ali")
                        .lastName("Ben Salah")
                        .email("ali.bensalah@example.com")
                        .phone("99887766")
                        .passportNo("CC998877")
                        .address("Sfax, Tunisia")
                        .build();

                travelerRepository.saveAll(List.of(t1, t2, t3));
                System.out.println("Sample travelers inserted successfully!");
            }

            // --------- FLIGHTS ---------
            if (flightRepository.count() == 0) {
                Flight f1 = Flight.builder()
                        .company("Air France")
                        .code("AF123")
                        .origin("Paris")
                        .destination("New York")
                        .departureTime(LocalDateTime.of(2026, 1, 10, 10, 0))
                        .arrivalTime(LocalDateTime.of(2026, 1, 10, 18, 0))
                        .totalSeats(200)
                        .flightStatus(FlightStatus.SCHEDULED)
                        .build();

                Flight f2 = Flight.builder()
                        .company("Tunisair")
                        .code("TU987")
                        .origin("Tunis")
                        .destination("Rome")
                        .departureTime(LocalDateTime.of(2026, 2, 5, 8, 30))
                        .arrivalTime(LocalDateTime.of(2026, 2, 5, 11, 0))
                        .totalSeats(180)
                        .flightStatus(FlightStatus.SCHEDULED)
                        .build();

                Flight f3 = Flight.builder()
                        .company("Lufthansa")
                        .code("LH456")
                        .origin("Frankfurt")
                        .destination("Tokyo")
                        .departureTime(LocalDateTime.of(2026, 3, 15, 12, 0))
                        .arrivalTime(LocalDateTime.of(2026, 3, 16, 6, 0))
                        .totalSeats(250)
                        .flightStatus(FlightStatus.SCHEDULED)
                        .build();

                Flight f4 = Flight.builder()
                        .company("Turkish Airlines")
                        .code("TR987")
                        .origin("Boston")
                        .destination("Turkey")
                        .departureTime(LocalDateTime.of(2026, 4, 8, 8, 25))
                        .arrivalTime(LocalDateTime.of(2026, 4, 9, 1, 12))
                        .totalSeats(250)
                        .flightStatus(FlightStatus.SCHEDULED)
                        .build();

                flightRepository.saveAll(List.of(f1, f2, f3, f4));
                System.out.println("Sample flights inserted successfully!");
            }

            // --------- TICKETS ---------
            if (ticketRepository.count() == 0 &&
                    travelerRepository.count() > 0 &&
                    flightRepository.count() > 0) {

                List<Traveler> allTravelers = travelerRepository.findAll();
                List<Flight> allFlights = flightRepository.findAll();

                Ticket ticket1 = Ticket.builder()
                        .createdAt(LocalDateTime.now())
                        .ticketStatus(TicketStatus.ACTIVE)
                        .price(new BigDecimal("350.00"))
                        .traveler(allTravelers.get(0))
                        .flight(allFlights.get(0))
                        .build();

                Ticket ticket2 = Ticket.builder()
                        .createdAt(LocalDateTime.now())
                        .ticketStatus(TicketStatus.ACTIVE)
                        .price(new BigDecimal("410.00"))
                        .traveler(allTravelers.get(1))
                        .flight(allFlights.get(1))
                        .build();

                Ticket ticket3 = Ticket.builder()
                        .createdAt(LocalDateTime.now())
                        .ticketStatus(TicketStatus.ACTIVE)
                        .price(new BigDecimal("520.00"))
                        .traveler(allTravelers.get(2))
                        .flight(allFlights.get(2))
                        .build();

                Ticket ticket4 = Ticket.builder()
                        .createdAt(LocalDateTime.now())
                        .ticketStatus(TicketStatus.CANCELED)
                        .price(new BigDecimal("280.00"))
                        .traveler(allTravelers.get(0))
                        .flight(allFlights.get(1))
                        .build();

                Ticket ticket5 = Ticket.builder()
                        .createdAt(LocalDateTime.now())
                        .ticketStatus(TicketStatus.ACTIVE)
                        .price(new BigDecimal("610.00"))
                        .traveler(allTravelers.get(1))
                        .flight(allFlights.get(2))
                        .build();

                ticketRepository.saveAll(List.of(ticket1, ticket2, ticket3, ticket4, ticket5));

                System.out.println("Sample tickets inserted successfully!");
            }
        };
    }
}
