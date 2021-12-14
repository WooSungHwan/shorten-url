package com.example.shortenurl.data;

import lombok.Value;

@Value(staticConstructor = "of")
public class ShortenUrl {

    private ShortenPath shortenPath;

    private OriginPath originPath;

}
