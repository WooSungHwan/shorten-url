package com.example.shortenurl.configuration;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class ErrorResponse {

    private String message;

    private List<String> details;

}
