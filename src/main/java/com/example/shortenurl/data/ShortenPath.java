package com.example.shortenurl.data;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@ToString(of = "path")
@EqualsAndHashCode(of = "path")
@Value(staticConstructor = "of")
public class ShortenPath {

    private String path;

}
