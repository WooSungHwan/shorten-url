package com.example.shortenurl.data;

import com.example.shortenurl.configuration.exception.VerifyException;
import com.example.shortenurl.utils.VerifyUtil;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@ToString(of = "path")
@EqualsAndHashCode(of = "path")
public class ShortenPath {

    private String path;

    public static ShortenPath of(String path) {
        VerifyUtil.isTrue(StringUtils.isNotBlank(path), "A path value is required");
        return new ShortenPath(path);
    }

}
