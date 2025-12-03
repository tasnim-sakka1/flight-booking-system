package com.iit.flightbooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iit.flightbooking.enums.FlightStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Flight Entity
 *
 * @author Tasnim
 * @since 2025-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Check(constraints = "arrival_time > departure_time")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String company;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String code;

    @NotBlank
    @Column(nullable = false)
    private String origin;

    @NotBlank
    @Column(nullable = false)
    private String destination;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime departureTime;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Positive
    @Column(nullable = false)
    private Integer totalSeats;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;

    private Integer delayMinutes;
    private LocalDateTime newDepartureTime;


    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Ticket> tickets;


    @AssertTrue(message = "departureTime must be BEFORE arrivalTime")
    public boolean isDepartureBeforeArrival() {
        return departureTime == null || arrivalTime == null || departureTime.isBefore(arrivalTime);
    }



}
