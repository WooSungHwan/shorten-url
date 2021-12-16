package com.example.shortenurl.component;

import com.example.shortenurl.data.ShortenPath;

public interface ShortenUrlGenerator {

    String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    String REDIRECT_URL = "http://localhost";

    String generate(long id);

    Long parse(ShortenPath shortenPath);

}
