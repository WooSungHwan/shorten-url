package com.example.shortenurl.data;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Objects;

@EqualsAndHashCode(of = "id")
@Value(staticConstructor = "of")
public class OriginUrl {

    private Long id;

    private OriginPath originPath;

    public boolean equalsOriginPath(OriginPath originPath) {
        return Objects.equals(originPath, this.originPath);
    }

}
