package com.iit.flightbooking.mappers;

import com.iit.flightbooking.dtos.TicketDto;
import com.iit.flightbooking.dtos.TravelerDto;
import com.iit.flightbooking.entities.Ticket;
import com.iit.flightbooking.entities.Traveler;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-17T22:29:53+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class TicketMapperImpl implements TicketMapper {

    @Override
    public TicketDto toDto(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketDto.TicketDtoBuilder ticketDto = TicketDto.builder();

        ticketDto.id( ticket.getId() );
        ticketDto.createdAt( ticket.getCreatedAt() );
        ticketDto.ticketStatus( ticket.getTicketStatus() );
        ticketDto.price( ticket.getPrice() );
        ticketDto.traveler( travelerToTravelerDto( ticket.getTraveler() ) );

        return ticketDto.build();
    }

    @Override
    public List<TicketDto> toDto(List<Ticket> tickets) {
        if ( tickets == null ) {
            return null;
        }

        List<TicketDto> list = new ArrayList<TicketDto>( tickets.size() );
        for ( Ticket ticket : tickets ) {
            list.add( toDto( ticket ) );
        }

        return list;
    }

    @Override
    public Ticket toEntity(TicketDto dto) {
        if ( dto == null ) {
            return null;
        }

        Ticket.TicketBuilder ticket = Ticket.builder();

        ticket.id( dto.getId() );
        ticket.createdAt( dto.getCreatedAt() );
        ticket.ticketStatus( dto.getTicketStatus() );
        ticket.price( dto.getPrice() );
        ticket.traveler( travelerDtoToTraveler( dto.getTraveler() ) );

        return ticket.build();
    }

    @Override
    public List<Ticket> toEntity(List<TicketDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Ticket> list = new ArrayList<Ticket>( dtos.size() );
        for ( TicketDto ticketDto : dtos ) {
            list.add( toEntity( ticketDto ) );
        }

        return list;
    }

    protected TravelerDto travelerToTravelerDto(Traveler traveler) {
        if ( traveler == null ) {
            return null;
        }

        TravelerDto.TravelerDtoBuilder travelerDto = TravelerDto.builder();

        travelerDto.id( traveler.getId() );
        travelerDto.firstName( traveler.getFirstName() );
        travelerDto.lastName( traveler.getLastName() );
        travelerDto.email( traveler.getEmail() );
        travelerDto.phone( traveler.getPhone() );
        travelerDto.passportNo( traveler.getPassportNo() );
        travelerDto.address( traveler.getAddress() );
        travelerDto.tickets( toDto( traveler.getTickets() ) );

        return travelerDto.build();
    }

    protected Traveler travelerDtoToTraveler(TravelerDto travelerDto) {
        if ( travelerDto == null ) {
            return null;
        }

        Traveler.TravelerBuilder traveler = Traveler.builder();

        traveler.id( travelerDto.getId() );
        traveler.firstName( travelerDto.getFirstName() );
        traveler.lastName( travelerDto.getLastName() );
        traveler.email( travelerDto.getEmail() );
        traveler.phone( travelerDto.getPhone() );
        traveler.passportNo( travelerDto.getPassportNo() );
        traveler.address( travelerDto.getAddress() );
        traveler.tickets( toEntity( travelerDto.getTickets() ) );

        return traveler.build();
    }
}
