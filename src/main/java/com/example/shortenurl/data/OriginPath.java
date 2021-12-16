package com.example.shortenurl.data;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.net.URI;

@ToString(of = "path")
@EqualsAndHashCode(of = "path")
@Value(staticConstructor = "of")
public class OriginPath {

    private String path;

    public URI toUri() {
        return URI.create(this.path);
    }

}
