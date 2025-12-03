package com.iit.flightbooking.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Arrays;

@RestController
public class DestinationRestController {

    @GetMapping("/api/destinations")
    public List<String> getDestinations() {
        return Arrays.asList("Paris", "Tokyo", "New York", "Tunis");
    }
}