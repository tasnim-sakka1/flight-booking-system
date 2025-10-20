package com.iit.flightbooking.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Traveler Entity
 *
 * @author Tasnim
 * @since 2025-10
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Traveler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;


    @Size(min = 8, max = 8, message = "Must be exactly 8 characters")
    @Column(name = "passport_no", unique = true, nullable = false)
    private String passportNo;

    private String address;

}
