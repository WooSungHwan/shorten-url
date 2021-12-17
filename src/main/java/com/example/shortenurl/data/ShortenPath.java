package com.example.shortenurl.data;

import com.example.shortenurl.configuration.PathRequiredException;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
@ToString(of = "path")
@EqualsAndHashCode(of = "path")
public class ShortenPath {

    private String path;

    public static ShortenPath of(String path) {
        if (StringUtils.isBlank(path)) {
            throw new PathRequiredException("path 정보가 필요합니다.");
        }
        return new ShortenPath(path);
    }

}
