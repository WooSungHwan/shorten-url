package com.example.shortenurl.data;

import com.example.shortenurl.utils.VerifyUtil;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@ToString(of = "path")
@EqualsAndHashCode(of = "path")
public class OriginPath {

    private String path;

    public static OriginPath of(String path) {
        VerifyUtil.isTrue(StringUtils.isNotBlank(path), "A path value is required");
        return new OriginPath(path);
    }

    public URI toUri() {
        return URI.create(this.path);
    }

}
