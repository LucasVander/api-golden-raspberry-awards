package com.teste.golden_raspberry_awards.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAwardsInterval() throws Exception {
        mockMvc.perform(get("/api/golden-raspberry-awards/movie/interval"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("{\"min\":[{\"producer\":\"Columbia Pictures\",\"interval\":1,\"previousWin\":2017,\"followingWin\":2018}],\"max\":[{\"producer\":\"Columbia Pictures\",\"interval\":24,\"previousWin\":1987,\"followingWin\":2011}]}"));
    }
    
    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/golden-raspberry-awards/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}