package com.iit.flightbooking.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelerDto {

    private Long id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String phone;

    @Size(min = 8, max = 8, message = "Must be exactly 8 characters")
    @NotBlank
    private String passportNo;

    private String address;

    private List<TicketDto> tickets;
}
