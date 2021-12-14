package com.example.shortenurl.component;

public interface ShortenUrlGenerator {

    int SHORTEN_URL_LENGTH = 8;

    String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    String generate(long id);

    Long parse(String shortenPath);

}
