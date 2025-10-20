package com.iit.flightbooking.services;

import com.iit.flightbooking.entities.Traveler;

import java.util.List;

/**
 * Traveler Service Interface
 *
 * @author Tasnim
 * @since 2025-10
 */
public interface TravelerService {

    /**
     * Creates and saves a new {@link Traveler} in the database.
     *
     * @param t the traveler to create
     * @return the created traveler with its generated ID
     */
    Traveler create(Traveler t);

    /**
     * Retrieves all travelers stored in the database.
     *
     * @return a list of all travelers
     */
    List<Traveler> findAll();

    /**
     * Retrieves a traveler by its unique ID.
     *
     * @param id the ID of the traveler to retrieve
     * @return the found traveler
     */
    Traveler findById(Long id);

    /**
     * Updates an existing traveler’s details.
     * Only non-null values in the provided traveler object will overwrite
     * the existing values. This allows partial updates.
     *
     * @param id the ID of the traveler to update
     * @param t  the traveler object containing new values
     * @return the updated traveler
     */
    Traveler update(Long id, Traveler t);

    /**
     * Deletes a traveler by its ID.
     *
     * @param id the ID of the traveler to delete
     */
    void deleteById(Long id);
}
