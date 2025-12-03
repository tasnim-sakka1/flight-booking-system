package com.iit.flightbooking.controllers;


import com.iit.flightbooking.dtos.TravelerDto;
import com.iit.flightbooking.entities.Traveler;
import com.iit.flightbooking.mappers.TravelerMapper;
import com.iit.flightbooking.services.TravelerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Traveler Controller
 *
 * @author Tasnim
 * @since 2025-10
 */
@RestController
@RequestMapping("/travelers")
public class TravelerController {

    @Autowired
    private TravelerMapper travelerMapper;

    @Autowired
    private TravelerService travelerService;

    //    CREATE
    /*
         POST: http://localhost:8088/travelers
        {
            "firstName": "Tasnim",
                "lastName": "Sakka",
                "email": "tasnim.sakka@example.com",
                "phone": "+21652000000",
                "passportNo": "TSN123456"
        }
        */
//    @PostMapping
//    public ResponseEntity<Traveler> create(@Valid @RequestBody Traveler body) {
//        Traveler createTraveler = travelerService.create(body);
//        return new ResponseEntity<>(createTraveler, HttpStatus.CREATED); // 201
//    }
    @PostMapping
    public ResponseEntity<TravelerDto> create(@Valid @RequestBody TravelerDto travelerDto) {
        Traveler traveler = travelerMapper.toEntity(travelerDto);
        Traveler savedTraveler = travelerService.create(traveler);
        TravelerDto responseDto = travelerMapper.toDto(savedTraveler);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //    READ ALL
    //    GET http://localhost:8088/travelers
//    @GetMapping
//    public ResponseEntity<List<Traveler>> findAll() {
//        List<Traveler> travelers = travelerService.findAll();
//        return new ResponseEntity<>(travelers, HttpStatus.OK); // 200
//    }
    @GetMapping
    public ResponseEntity<List<TravelerDto>> findAll() {
        List<TravelerDto> travelers = travelerMapper.toDto(travelerService.findAll());
        return new ResponseEntity<>(travelers, HttpStatus.OK); // 200
    }

    //    READ ONE
    //    GET http://localhost:8088/travelers/1
//    @GetMapping("/{id}")
//    public ResponseEntity<Traveler> getById(@PathVariable Long id) {
//        Traveler foundTraveler = travelerService.findById(id);
//        return new ResponseEntity<>(foundTraveler, HttpStatus.OK); // 200 or throws 404
//    }
    @GetMapping("/{id}")
    public ResponseEntity<TravelerDto> getById(@PathVariable Long id) {
        TravelerDto traveler = travelerMapper.toDto(travelerService.findById(id));
        return new ResponseEntity<>(traveler, HttpStatus.OK); // 200 or throws 404
    }


    //    UPDATE
    /*
        PUT: http://localhost:8088/travelers/1
        {
            "firstName": "Tasnim",
                "lastName": "Sakka",
                "email": "tasnim.sakka@example.com",
                "phone": "+21652000000",
                "passportNo": "TSN123456"
        }*/
//    @PutMapping("/{id}")
//    public ResponseEntity<Traveler> update(@PathVariable Long id, @Valid @RequestBody Traveler body) {
//        return ResponseEntity.ok(travelerService.update(id, body)); // 200
//    }
    @PutMapping("/{id}")
    public ResponseEntity<TravelerDto> update( @PathVariable Long id, @Valid @RequestBody TravelerDto traveler) {
        TravelerDto travelerDto = travelerMapper.toDto(travelerService.update(id, travelerMapper.toEntity(traveler)));
        return ResponseEntity.ok(travelerDto); // 200
    }

    //    DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        travelerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

}
