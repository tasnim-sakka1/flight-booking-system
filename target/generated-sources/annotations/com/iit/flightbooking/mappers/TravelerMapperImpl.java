package com.iit.flightbooking.mappers;

import com.iit.flightbooking.dtos.TravelerDto;
import com.iit.flightbooking.entities.Traveler;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-17T22:29:54+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class TravelerMapperImpl implements TravelerMapper {

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public TravelerDto toDto(Traveler traveler) {
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

        return travelerDto.build();
    }

    @Override
    public List<TravelerDto> toDto(List<Traveler> travelers) {
        if ( travelers == null ) {
            return null;
        }

        List<TravelerDto> list = new ArrayList<TravelerDto>( travelers.size() );
        for ( Traveler traveler : travelers ) {
            list.add( toDto( traveler ) );
        }

        return list;
    }

    @Override
    public Traveler toEntity(TravelerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Traveler.TravelerBuilder traveler = Traveler.builder();

        traveler.id( dto.getId() );
        traveler.firstName( dto.getFirstName() );
        traveler.lastName( dto.getLastName() );
        traveler.email( dto.getEmail() );
        traveler.phone( dto.getPhone() );
        traveler.passportNo( dto.getPassportNo() );
        traveler.address( dto.getAddress() );
        traveler.tickets( ticketMapper.toEntity( dto.getTickets() ) );

        return traveler.build();
    }

    @Override
    public List<Traveler> toEntity(List<TravelerDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Traveler> list = new ArrayList<Traveler>( dtos.size() );
        for ( TravelerDto travelerDto : dtos ) {
            list.add( toEntity( travelerDto ) );
        }

        return list;
    }
}
