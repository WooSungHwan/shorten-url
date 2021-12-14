package com.example.shortenurl.request;

import com.example.shortenurl.URL;
import lombok.*;
import org.apache.commons.validator.routines.UrlValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.net.URISyntaxException;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
public class ShortenUrlRequest {
    @URL
    @NotBlank(message = "원본 url을 입력해주시기 바랍니다.")
    private String originUrl;
}
