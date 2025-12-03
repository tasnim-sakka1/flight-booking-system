package com.iit.flightbooking;

import com.iit.flightbooking.entities.Traveler;
import com.iit.flightbooking.repositories.TravelerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seedTravelers(TravelerRepository travelerRepository) {
        return args -> {
            if (travelerRepository.count() == 0) {
                Traveler t1 = Traveler.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("john.doe@example.com")
                        .phone("12345678")
                        .passportNo("AA123456")
                        .address("New York, USA")
                        .build();

                Traveler t2 = Traveler.builder()
                        .firstName("Sara")
                        .lastName("Smith")
                        .email("sara.smith@example.com")
                        .phone("87654321")
                        .passportNo("BB654321")
                        .address("Toronto, Canada")
                        .build();

                Traveler t3 = Traveler.builder()
                        .firstName("Ali")
                        .lastName("Ben Salah")
                        .email("ali.bensalah@example.com")
                        .phone("99887766")
                        .passportNo("CC998877")
                        .address("Sfax, Tunisia")
                        .build();

                travelerRepository.save(t1);
                travelerRepository.save(t2);
                travelerRepository.save(t3);

                System.out.println("Sample travelers inserted successfully!");
            } else {
                System.out.println("Travelers already exist, skipping seeding.");
            }
        };
    }
}