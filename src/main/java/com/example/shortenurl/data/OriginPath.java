package com.example.shortenurl.data;

import com.example.shortenurl.configuration.PathRequiredException;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;

@Getter
@AllArgsConstructor
@ToString(of = "path")
@EqualsAndHashCode(of = "path")
public class OriginPath {

    private String path;

    public static OriginPath of(String path) {
        if (StringUtils.isBlank(path)) {
            throw new PathRequiredException("path 정보가 필요합니다.");
        }
        return new OriginPath(path);
    }

    public URI toUri() {
        return URI.create(this.path);
    }

}
