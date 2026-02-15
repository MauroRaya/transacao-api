package com.mauroraya.transacao_api.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.mauroraya.transacao_api.requests.CriarTransacaoRequest;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveRetornar422QuandoValorForNegativo() throws Exception {
        CriarTransacaoRequest request = new CriarTransacaoRequest(
            -1,
            OffsetDateTime.now()
        );

        String content = objectMapper.writeValueAsString(request);

        MockHttpServletRequestBuilder requestBuilder = post("/transacao")
            .contentType(APPLICATION_JSON)
            .content(content);

        mvc.perform(requestBuilder)
            .andExpect(status().isUnprocessableContent());
    }

    @Test
    public void deveRetornar422QuandoDataHoraForNoFuturo() throws Exception {
        CriarTransacaoRequest request = new CriarTransacaoRequest(
            0,
            OffsetDateTime.now().plusSeconds(1)
        );

        String content = objectMapper.writeValueAsString(request);

        MockHttpServletRequestBuilder requestBuilder = post("/transacao")
            .contentType(APPLICATION_JSON)
            .content(content);

        mvc.perform(requestBuilder)
            .andExpect(status().isUnprocessableContent());
    }

    @Test
    public void deveRetornar201QuandoCorpoForValido() throws Exception {
        CriarTransacaoRequest request = new CriarTransacaoRequest(
            0,
            OffsetDateTime.now()
        );

        String content = objectMapper.writeValueAsString(request);

        MockHttpServletRequestBuilder requestBuilder = post("/transacao")
            .contentType(APPLICATION_JSON)
            .content(content);

        mvc.perform(requestBuilder)
            .andExpect(status().isCreated());
    }
}
