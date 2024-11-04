package com.example.atostest.controllers;

import com.example.atostest.entities.Spaceship;
import com.example.atostest.services.SpaceshipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpaceshipController.class)
class SpaceshipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpaceshipService spaceshipService;

    @Test
    void testCreateSpaceship() throws Exception {
        Spaceship spaceship = new Spaceship("X-WING");
        when(spaceshipService.saveSpaceship(any())).thenReturn(spaceship);

        mockMvc.perform(MockMvcRequestBuilders.post("/spaceships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"X-WING\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("X-WING"));
    }

    @Test
    void testGetSpaceshipById() throws Exception {
        Spaceship spaceship = new Spaceship("X-WING");
        when(spaceshipService.getSpaceshipById(1L)).thenReturn(Optional.of(spaceship));

        mockMvc.perform(MockMvcRequestBuilders.get("/spaceships/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("X-WING"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value("Red"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.spaceshipSize").value("SMALL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.visualChannel").value("FILM"));
    }

    @Test
    void testGetSpaceshipByIdNotFound() throws Exception {
        when(spaceshipService.getSpaceshipById(anyLong())).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/spaceships/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetSpaceshipByIdNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/spaceships/"))
                .andExpect(status().isBadRequest());
    }
}