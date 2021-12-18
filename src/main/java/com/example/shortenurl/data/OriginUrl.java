package com.example.shortenurl.data;

import com.example.shortenurl.utils.VerifyUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode(of = "id")
public class OriginUrl {

    private Long id;

    private OriginPath originPath;

    public static OriginUrl of(Long id, OriginPath originPath) {
        VerifyUtil.isTrue(id > 0, "id must be positive value!!");
        VerifyUtil.nonNull(id, "id must be not null!!");
        VerifyUtil.nonNull(originPath, "originPath must be not null!!");
        return new OriginUrl(id, originPath);
    }

    public boolean equalsOriginPath(OriginPath originPath) {
        return Objects.equals(originPath, this.originPath);
    }

}