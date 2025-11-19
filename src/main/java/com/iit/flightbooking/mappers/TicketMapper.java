

package com.iit.flightbooking.mappers;

import com.iit.flightbooking.dtos.TicketDto;
import com.iit.flightbooking.entities.Ticket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // 🔹 Very important for Spring to detect it as a bean
public interface TicketMapper {

    TicketDto toDto(Ticket ticket);

    List<TicketDto> toDto(List<Ticket> tickets);

    Ticket toEntity(TicketDto dto);

    List<Ticket> toEntity(List<TicketDto> dtos);
}

