package com.example.shortenurl.data;

import com.example.shortenurl.utils.VerifyUtil;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

import java.net.URI;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode(of = {"shortenPath", "originPath"})
public class ShortenUrl {

    private ShortenPath shortenPath;

    private OriginPath originPath;

    public static ShortenUrl of(ShortenPath shortenPath, OriginPath originPath) {
        VerifyUtil.nonNull(shortenPath, "shortenPath must be not null!!");
        VerifyUtil.nonNull(originPath, "originPath must be not null!!");
        return new ShortenUrl(shortenPath, originPath);
    }

}