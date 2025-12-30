package com.iit.flightbooking.mappers;

import com.iit.flightbooking.dtos.FlightDto;
import com.iit.flightbooking.entities.Flight;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-17T22:29:54+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public FlightDto toDto(Flight flight) {
        if ( flight == null ) {
            return null;
        }

        FlightDto.FlightDtoBuilder flightDto = FlightDto.builder();

        flightDto.id( flight.getId() );
        flightDto.company( flight.getCompany() );
        flightDto.code( flight.getCode() );
        flightDto.origin( flight.getOrigin() );
        flightDto.destination( flight.getDestination() );
        flightDto.departureTime( flight.getDepartureTime() );
        flightDto.arrivalTime( flight.getArrivalTime() );
        flightDto.totalSeats( flight.getTotalSeats() );
        flightDto.flightStatus( flight.getFlightStatus() );
        flightDto.delayMinutes( flight.getDelayMinutes() );
        flightDto.newDepartureTime( flight.getNewDepartureTime() );

        return flightDto.build();
    }

    @Override
    public List<FlightDto> toDto(List<Flight> flights) {
        if ( flights == null ) {
            return null;
        }

        List<FlightDto> list = new ArrayList<FlightDto>( flights.size() );
        for ( Flight flight : flights ) {
            list.add( toDto( flight ) );
        }

        return list;
    }

    @Override
    public Flight toEntity(FlightDto dto) {
        if ( dto == null ) {
            return null;
        }

        Flight.FlightBuilder flight = Flight.builder();

        flight.id( dto.getId() );
        flight.company( dto.getCompany() );
        flight.code( dto.getCode() );
        flight.origin( dto.getOrigin() );
        flight.destination( dto.getDestination() );
        flight.departureTime( dto.getDepartureTime() );
        flight.arrivalTime( dto.getArrivalTime() );
        flight.totalSeats( dto.getTotalSeats() );
        flight.flightStatus( dto.getFlightStatus() );
        flight.delayMinutes( dto.getDelayMinutes() );
        flight.newDepartureTime( dto.getNewDepartureTime() );

        return flight.build();
    }

    @Override
    public List<Flight> toEntity(List<FlightDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Flight> list = new ArrayList<Flight>( dtos.size() );
        for ( FlightDto flightDto : dtos ) {
            list.add( toEntity( flightDto ) );
        }

        return list;
    }
}
