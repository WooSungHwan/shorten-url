package com.example.shortenurl.component;

import com.example.shortenurl.data.ShortenPath;

public interface ShortenUrlGenerator {

    int SHORTEN_URL_LENGTH = 8;

    String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    String REDIRECT_URL = "http://localhost:8080";

    String generate(long id);

    Long parse(ShortenPath shortenPath);

}
