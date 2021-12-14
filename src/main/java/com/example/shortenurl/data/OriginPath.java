package com.example.shortenurl.data;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(of = "path")
@Value(staticConstructor = "of")
public class OriginPath {

    private String path;

}
