
package com.iit.flightbooking.mappers;

import com.iit.flightbooking.dtos.TravelerDto;
import com.iit.flightbooking.entities.Traveler;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = TicketMapper.class)
public interface TravelerMapper {

    //  Break the cycle here
    @Mapping(target = "tickets", ignore = true)
    TravelerDto toDto(Traveler traveler);

    List<TravelerDto> toDto(List<Traveler> travelers);

    Traveler toEntity(TravelerDto dto);

    List<Traveler> toEntity(List<TravelerDto> dtos);

}
