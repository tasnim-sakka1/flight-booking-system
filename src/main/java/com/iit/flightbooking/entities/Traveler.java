package com.iit.flightbooking.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;


    @Email
    @NotBlank
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "phone", nullable = false)
    private String phone;


    @Size(min = 8, max = 8, message = "Must be exactly 8 characters")
    @Column(name = "passport_no", unique = true, nullable = false)
    private String passportNo;

    private String address;

//    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL, orphanRemoval = true)
////    @JsonIgnore
//    private List<Ticket> tickets;

}
