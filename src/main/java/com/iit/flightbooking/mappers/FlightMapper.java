package com.iit.flightbooking.mappers;

import com.iit.flightbooking.dtos.FlightDto;
import com.iit.flightbooking.entities.Flight;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // very important for Spring to detect it as a bean
public interface FlightMapper {

    FlightDto toDto(Flight flight);

    List<FlightDto> toDto(List<Flight> flights);

    Flight toEntity(FlightDto dto);

    List<Flight> toEntity(List<FlightDto> dtos);

}