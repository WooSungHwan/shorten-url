package com.example.shortenurl.utils;

import org.springframework.stereotype.Component;

public interface ShortenUrlGenerator {

    int SHORTEN_URL_LENGTH = 8;

    String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public String generate(String shortenUrlKey);

}
