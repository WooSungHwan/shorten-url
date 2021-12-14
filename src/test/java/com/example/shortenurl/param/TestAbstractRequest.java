package com.example.shortenurl.param;

import com.example.shortenurl.request.ShortenUrlRequest;
import org.junit.jupiter.api.BeforeEach;

import javax.validation.Validation;
import javax.validation.Validator;

public abstract class TestAbstractRequest {

    protected Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    protected ShortenUrlRequest makeRequest(String originUrl) {
        ShortenUrlRequest request = ShortenUrlRequest.builder()
                .originUrl(originUrl)
                .build();
        return request;
    }

}
