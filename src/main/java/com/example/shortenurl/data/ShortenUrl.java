package com.example.shortenurl.data;

import lombok.Value;

import java.net.URI;

@Value(staticConstructor = "of")
public class ShortenUrl {

    private ShortenPath shortenPath;

    private OriginPath originPath;

    public URI toOriginUrl() {
        return URI.create(originPath.getPath());
    }

}
