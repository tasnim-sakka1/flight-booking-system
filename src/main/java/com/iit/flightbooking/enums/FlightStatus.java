package com.iit.flightbooking.enums;

/**
 * Flight status enum.
 *
 * @author Tasnim
 * @since 2025-10
 */
public enum FlightStatus {
    SCHEDULED,   // planned, not yet departed
    DELAYED,     // departure postponed
    DEPARTED,    // already left
    ARRIVED,     // landed at destination
    CANCELED     // flight cancelled
}