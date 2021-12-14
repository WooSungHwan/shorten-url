package com.example.shortenurl.data;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(of = "path")
@Value(staticConstructor = "of")
public class ShortenPath {

    private String path;

    public static ShortenPath of(String redirectUrl, String shortenUrlKey) {
        return ShortenPath.of(String.format("%s/%s", redirectUrl, shortenUrlKey));
    }

}
