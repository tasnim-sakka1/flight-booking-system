package com.iit.flightbooking.repositories;

import com.iit.flightbooking.entities.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Traveler Repository Interface
 *
 * @author Tasnim
 * @since 2025-10
 */
@Repository
public interface TravelerRepository extends JpaRepository<Traveler, Long> {

    Traveler findByEmail(String email);
    Traveler findByPassportNo(String passportNo);

    // These are useful for update validation
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByPassportNoAndIdNot(String passportNo, Long id);


}
