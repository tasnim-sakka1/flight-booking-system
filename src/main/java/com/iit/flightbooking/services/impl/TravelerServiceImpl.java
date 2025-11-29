package com.iit.flightbooking.services.impl;

import com.iit.flightbooking.entities.Traveler;
import com.iit.flightbooking.repositories.TravelerRepository;
import com.iit.flightbooking.services.TravelerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return travelerRepository.save(t);
    }

    @Override
    public List<Traveler> findAll() {
        return travelerRepository.findAll();
    }

    @Override
    public Traveler findById(Long id) {
        return travelerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Traveler " + id + " not found"));
    }

    @Override
    public Traveler update(Long id, Traveler t) {
        Traveler existing = findById(id);
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
