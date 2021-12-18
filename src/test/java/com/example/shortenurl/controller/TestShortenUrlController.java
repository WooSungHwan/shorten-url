package com.example.shortenurl.controller;

import com.example.shortenurl.request.ShortenUrlRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Attributes;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestShortenUrlController extends TestAbstractController {

    @DisplayName("[POST] /shorten")
    @Test
    void getShortenUrl() throws Exception {
        ShortenUrlRequest shortenUrlRequest = ShortenUrlRequest.builder()
                .originUrl("https://www.musinsa.com")
                .build();

        mockMvc.perform(post("/shorten")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.valueToTree(shortenUrlRequest).toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                    requestFields(
                        fieldWithPath("originUrl").type(JsonFieldType.STRING).description("원본 url")
                    ),
                    responseFields(
                        fieldWithPath("originPath").type(JsonFieldType.OBJECT).description("원본 url 객체"),
                        fieldWithPath("originPath.path").type(JsonFieldType.STRING).description("원본 url path")
                                .attributes(new Attributes.Attribute("format", "https://www.musinsa.com")),
                        fieldWithPath("shortenPath").type(JsonFieldType.OBJECT).description("짧은 url 객체"),
                        fieldWithPath("shortenPath.path").type(JsonFieldType.STRING).description("짧은 url path")
                                .attributes(new Attributes.Attribute("format", "http://localhost/ESASS"))
                    )
            ));
    }

}