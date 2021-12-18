package com.example.shortenurl.controller;

import com.example.shortenurl.configuration.EnableMockMvc;
import com.example.shortenurl.request.ShortenUrlRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableMockMvc
@SpringBootTest
public class TestShortenUrlController {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("[POST] /api/v1/shorten-url API에 대한 통합테스트")
    @Test
    void getShortenUrl() throws Exception {
        ShortenUrlRequest shortenUrlRequest = ShortenUrlRequest.builder()
                .originUrl("http://www.naver.com")
                .build();

        mockMvc.perform(post("/shorten")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(shortenUrlRequest)))
            .andDo(print())
            .andExpect(status().isOk());
    }

}