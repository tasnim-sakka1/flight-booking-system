package com.iit.flightbooking.dtos;


import com.iit.flightbooking.entities.Flight;
import com.iit.flightbooking.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Long id;
    private LocalDateTime createdAt;
    private TicketStatus ticketStatus;
    private BigDecimal price;

    private TravelerDto traveler;
//    private FlightDto flight; TODO

}
