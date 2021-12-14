package com.example.shortenurl.result;

import lombok.Value;

@Value(staticConstructor = "of")
public class ShortenUrlResult {
    private String shortenUrl;
}
