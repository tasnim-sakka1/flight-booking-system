package com.iit.flightbooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iit.flightbooking.enums.TicketStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Ticket Entity
 *
 * @author Tasnim
 * @since 2025-11
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "traveler_id", nullable = false)
    @JsonIgnore
    private Traveler traveler;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "flight_id", nullable = false)
    @JsonIgnore
    private Flight flight;

}
