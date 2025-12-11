package com.iit.flightbooking.services.impl;

import com.iit.flightbooking.entities.Traveler;
import com.iit.flightbooking.repositories.TravelerRepository;
import com.iit.flightbooking.services.impl.TravelerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Simple unit tests for TravelerServiceImpl
 * Style s (Mockito + assertAll)
 *
 *
 *
 * @author Nour Elaoud
 */
@ExtendWith(MockitoExtension.class)
class TravelerServiceTest {

    @Mock
    private TravelerRepository travelerRepository;

    @InjectMocks
    private TravelerServiceImpl travelerService; // adapt class name if needed

    // ---------- helpers ----------

    private static Traveler getTraveler() {
        return Traveler.builder()
                .id(1L)
                .firstName("Tasnim")
                .lastName("Sakka")
                .email("tasnim@example.com")
                .phone("+21652000000")
                .passportNo("TSN12345")
                .build();
    }

    private static Traveler getAnotherTraveler() {
        return Traveler.builder()
                .id(2L)
                .firstName("Ahmed")
                .lastName("Ali")
                .email("ahmed@example.com")
                .phone("+21653000000")
                .passportNo("AHMD1234")
                .build();
    }

    // ---------- tests ----------

    @Test
    void createTravelerTest() {
        // given
        Traveler input = getTraveler();
        input.setId(null); // new traveler

        when(travelerRepository.findByEmail(input.getEmail())).thenReturn(null);
        when(travelerRepository.findByPassportNo(input.getPassportNo())).thenReturn(null);
        when(travelerRepository.save(any(Traveler.class))).thenAnswer(inv -> {
            Traveler t = inv.getArgument(0);
            t.setId(1L);
            return t;
        });

        // when
        Traveler output = travelerService.create(input); // adapt method name if different

        // then
        verify(travelerRepository, times(1)).findByEmail(input.getEmail());
        verify(travelerRepository, times(1)).findByPassportNo(input.getPassportNo());
        verify(travelerRepository, times(1)).save(any(Traveler.class));

        assertAll(
                () -> assertNotNull(output),
                () -> assertNotNull(output.getId()),
                () -> assertEquals(input.getEmail(), output.getEmail()),
                () -> assertEquals(input.getPassportNo(), output.getPassportNo())
        );
    }

    @Test
    void getAllTravelersTest() {
        // given
        List<Traveler> inputList = Arrays.asList(getTraveler(), getAnotherTraveler());
        when(travelerRepository.findAll()).thenReturn(inputList);
        int expectedSize = 2;

        // when
        List<Traveler> outputList = travelerService.findAll(); // adapt name if needed

        // then
        verify(travelerRepository, times(1)).findAll();
        assertAll(
                () -> assertNotNull(outputList),
                () -> assertEquals(expectedSize, outputList.size()),
                () -> assertEquals(inputList.get(0).getEmail(), outputList.get(0).getEmail()),
                () -> assertEquals(inputList.get(1).getEmail(), outputList.get(1).getEmail())
        );
    }

    @Test
    void getTravelerByIdTest() {
        // given
        Traveler input = getTraveler();
        Long expectedId = 1L;
        when(travelerRepository.findById(anyLong())).thenReturn(Optional.of(input));

        // when
        Traveler output = travelerService.findById(expectedId); // adapt name if needed

        // then
        verify(travelerRepository, times(1)).findById(expectedId);
        assertAll(
                () -> assertNotNull(output),
                () -> assertNotNull(output.getId()),
                () -> assertEquals(expectedId, output.getId()),
                () -> assertEquals(input.getEmail(), output.getEmail())
        );
    }

    @Test
    void updateTravelerTest() {
        // given
        Traveler existing = getTraveler();

        when(travelerRepository.findById(anyLong())).thenReturn(Optional.of(existing));
        when(travelerRepository.existsByEmailAndIdNot("new@example.com", 1L)).thenReturn(false);
        when(travelerRepository.existsByPassportNoAndIdNot("NEWPA123", 1L)).thenReturn(false);
        when(travelerRepository.save(any(Traveler.class))).thenAnswer(inv -> inv.getArgument(0));

        Traveler update = new Traveler();
        update.setFirstName("NewName");
        update.setEmail("new@example.com");
        update.setPassportNo("NEWPA123");

        Long idToUpdate = 1L;

        // when
        Traveler output = travelerService.update(idToUpdate, update); // adapt name if needed

        // then
        verify(travelerRepository, times(1)).findById(idToUpdate);
        verify(travelerRepository, times(1)).save(any(Traveler.class));

        assertAll(
                () -> assertNotNull(output),
                () -> assertEquals("NewName", output.getFirstName()),
                () -> assertEquals("new@example.com", output.getEmail()),
                () -> assertEquals("NEWPA123", output.getPassportNo())
        );
    }

    @Test
    void deleteTravelerTest() {
        // given
        Traveler existing = getTraveler();
        Long idToDelete = 1L;

        when(travelerRepository.findById(idToDelete)).thenReturn(Optional.of(existing));
        doNothing().when(travelerRepository).delete(existing);

        // when
        travelerService.deleteById(idToDelete); // adapt name if needed

        // then
        verify(travelerRepository, times(1)).findById(idToDelete);
        verify(travelerRepository, times(1)).delete(existing);
    }
}
