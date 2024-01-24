package com.alexeyrand.justsymbols.api;

import com.alexeyrand.justsymbols.service.SymbolsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;


@ExtendWith(MockitoExtension.class)
public class SymbolControllerTest {


    @Mock
    private SymbolsService service;

    @InjectMocks
    private SymbolController controller;

    private MockMvc mockMvc;

//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }
//
//    @Test
//    void ok() throws Exception {
//        when(service.solve("aaaabbc")).thenReturn("{\"a\":4,\"b\":2,\"c\":1}");
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/symbols?str=aaaabbc"))
//               .andExpect(jsonPath("$.a").value(4)).;
//
//    }


    @Test
    public void testExceptionOnEmpty() {
        boolean exceptionThrown = false;
        try {
            controller.getSymbols("   ");
        } catch (BadRequestException BRE) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void testExceptionOnMaxLen() {
        boolean exceptionThrown = false;
        try {
            controller.getSymbols("aaaaddddqqqqeeeeeeeerrrrrrrttttttttttyyyyyyyyyyyuuuuuuuuuuuiiiiiiiii");
        } catch (BadRequestException BRE) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void testExceptionOnSpace() {
        boolean exceptionThrown = false;
        try {
            controller.getSymbols("aaaa ddd");
        } catch (BadRequestException BRE) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void resultt() {

        String res = controller.getSymbols("aaaabbc");
        assertEquals("{\"a\":4,\"b\":2,\"c\":1}", res);
    }

}
