package com.iit.flightbooking.repositories;

import com.iit.flightbooking.entities.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Traveler Repository Interface
 *
 * @author Tasnim
 * @since 2025-10
 */
public interface TravelerRepository extends JpaRepository<Traveler, Long> {
}
