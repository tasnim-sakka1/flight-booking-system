package com.iit.flightbooking.dtos;

import com.iit.flightbooking.enums.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDto {

    private Long id;
    private String company;
    private String code;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer totalSeats;
    private FlightStatus flightStatus;
    private Integer delayMinutes;
    private LocalDateTime newDepartureTime;

//    private List<TicketDto> tickets;

}

