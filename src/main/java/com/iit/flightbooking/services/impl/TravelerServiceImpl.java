package com.iit.flightbooking.services.impl;

import com.iit.flightbooking.entities.Traveler;
import com.iit.flightbooking.exceptions.APIException;
import com.iit.flightbooking.exceptions.ResourceNotFoundException;
import com.iit.flightbooking.repositories.TravelerRepository;
import com.iit.flightbooking.services.TravelerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Traveler Service Implementation
 *
 * @author Tasnim
 * @since 2025-10
 */
@Service
@Transactional
@RequiredArgsConstructor
public class TravelerServiceImpl implements TravelerService {

    @Autowired
    private TravelerRepository travelerRepository;

    @Override
    public Traveler create(Traveler t) {
        Traveler emailFound = travelerRepository.findByEmail(t.getEmail());
        if (emailFound != null)
            throw new APIException("Email already exists!");
        Traveler passportFound = travelerRepository.findByPassportNo(t.getPassportNo());
        if (passportFound != null)
            throw new APIException("Passport number already exists!");
        return travelerRepository.save(t);
    }

    @Override
    public List<Traveler> findAll() {
//        return travelerRepository.findAll();
        List<Traveler> travelers = travelerRepository.findAll();
        if (travelers.isEmpty())
            throw new APIException("No Traveler created till now.");
        return travelers;
    }

    @Override
    public Traveler findById(Long id) {
        return travelerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Traveler", "id", id));
    }

    @Override
    public Traveler update(Long id, Traveler t) {
        Traveler existing = findById(id); // travelerRepository.findById(id)

        // Check if new email is already used by another traveler
        if (t.getEmail() != null &&
                travelerRepository.existsByEmailAndIdNot(t.getEmail(), id)) {
            throw new APIException("Traveler with email '" + t.getEmail() + "' already exists!");
        }

        // Check if new passport number is already used by another traveler
        if (t.getPassportNo() != null &&
                travelerRepository.existsByPassportNoAndIdNot(t.getPassportNo(), id)) {
            throw new APIException("Traveler with passport number '" + t.getPassportNo() + "' already exists!");
        }

        // Only overwrite if the new value is not null
        existing.setFirstName(t.getFirstName() != null ? t.getFirstName() : existing.getFirstName());
        existing.setLastName(t.getLastName() != null ? t.getLastName() : existing.getLastName());
        existing.setEmail(t.getEmail() != null ? t.getEmail() : existing.getEmail()); // TODO
        existing.setPhone(t.getPhone() != null ? t.getPhone() : existing.getPhone());
        existing.setPassportNo(t.getPassportNo() != null ? t.getPassportNo() : existing.getPassportNo()); // TODO
        existing.setAddress(t.getAddress() != null ? t.getAddress() : existing.getAddress());

        return travelerRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        Traveler existing = findById(id);
        travelerRepository.delete(existing);
    }
}
