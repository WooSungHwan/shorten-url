package com.example.shortenurl.entity;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(of = "originUrl")
@Value(staticConstructor = "of")
public class ShortenUrl {

    private String originUrl;

}
