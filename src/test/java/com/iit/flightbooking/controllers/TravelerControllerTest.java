package com.iit.flightbooking.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iit.flightbooking.controllers.TravelerController;
import com.iit.flightbooking.dtos.TravelerDto;
import com.iit.flightbooking.entities.Traveler;
import com.iit.flightbooking.mappers.TravelerMapper;
import com.iit.flightbooking.services.TravelerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit tests for TravelerController
 * Uses MapStruct mapper as a Mockito @Mock
 *
 * @author Nour Elaoud
 */
@ExtendWith(MockitoExtension.class)
class TravelerControllerTest {

    @Mock
    private TravelerService travelerService;

    @Mock
    private TravelerMapper travelerMapper; // MapStruct mapper (mocked)

    @InjectMocks
    private TravelerController travelerController;

    private MockMvc mockMvc;

    // Jackson ObjectMapper for JSON <-> String in tests
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(travelerController).build();
    }

    private Traveler getTraveler() {
        return Traveler.builder()
                .id(1L)
                .firstName("Tasnim")
                .lastName("Sakka")
                .email("tasnim@example.com")
                .phone("+21652000000")
                .passportNo("TSN12345")
                .build();
    }

    private TravelerDto getTravelerDto() {
        return TravelerDto.builder()
                .id(1L)
                .firstName("Tasnim")
                .lastName("Sakka")
                .email("tasnim@example.com")
                .phone("+21652000000")
                .passportNo("TSN12345")
                .build();
    }

    private static String asJsonString(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    // ---------- tests ----------

    @Test
    void createTravelerTest() throws Exception {
        // given
        TravelerDto inputDto = getTravelerDto();
        Traveler entity = getTraveler();

        when(travelerMapper.toEntity(any(TravelerDto.class))).thenReturn(entity);
        when(travelerService.create(any(Traveler.class))).thenReturn(entity);
        when(travelerMapper.toDto(any(Traveler.class))).thenReturn(getTravelerDto());

        // when
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.post("/travelers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputDto))
        );

        // then
        verify(travelerService, times(1)).create(any(Traveler.class));
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is("tasnim@example.com")));
    }

    @Test
    void getAllTravelersTest() throws Exception {
        // given
        List<Traveler> travelers = List.of(getTraveler());
        List<TravelerDto> travelerDtos = List.of(getTravelerDto());

        when(travelerService.findAll()).thenReturn(travelers);
        when(travelerMapper.toDto(travelers)).thenReturn(travelerDtos);

        // when
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.get("/travelers")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        verify(travelerService, times(1)).findAll();
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", is(travelerDtos.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    void getTravelerByIdTest() throws Exception {
        // given
        Traveler traveler = getTraveler();
        TravelerDto travelerDto = getTravelerDto();
        Long idToFind = 1L;

        when(travelerService.findById(anyLong())).thenReturn(traveler);
        when(travelerMapper.toDto(traveler)).thenReturn(travelerDto);

        // when
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.get("/travelers/{id}", idToFind)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        verify(travelerService, times(1)).findById(idToFind);
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is("tasnim@example.com")));
    }

    @Test
    void updateTravelerTest() throws Exception {
        // given
        TravelerDto inputDto = getTravelerDto();
        inputDto.setFirstName("NewName");
        inputDto.setEmail("new@example.com");
        inputDto.setPassportNo("NEWPA123");

        Traveler updateEntity = getTraveler();
        updateEntity.setFirstName("NewName");
        updateEntity.setEmail("new@example.com");
        updateEntity.setPassportNo("NEWPA123");

        TravelerDto updatedDto = TravelerDto.builder()
                .id(1L)
                .firstName("NewName")
                .lastName("Sakka")
                .email("new@example.com")
                .phone("+21652000000")
                .passportNo("NEWPA123")
                .build();

        when(travelerMapper.toEntity(any(TravelerDto.class))).thenReturn(updateEntity);
        when(travelerService.update(anyLong(), any(Traveler.class))).thenReturn(updateEntity);
        when(travelerMapper.toDto(updateEntity)).thenReturn(updatedDto);

        Long idToUpdate = 1L;

        // when
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.put("/travelers/{id}", idToUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputDto))
        );

        // then
        verify(travelerService, times(1)).update(anyLong(), any(Traveler.class));
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("NewName")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is("new@example.com")));
    }

    @Test
    void deleteTravelerTest() throws Exception {
        // given
        Long idToDelete = 1L;
        doNothing().when(travelerService).deleteById(idToDelete);

        // when
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.delete("/travelers/{id}", idToDelete)
        );

        // then
        verify(travelerService, times(1)).deleteById(idToDelete);
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
