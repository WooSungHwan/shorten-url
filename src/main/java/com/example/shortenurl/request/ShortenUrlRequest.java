package com.example.shortenurl.request;

import com.example.shortenurl.configuration.validation.URL;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
public class ShortenUrlRequest {
    @URL
    @NotBlank(message = "원본 url을 입력해주시기 바랍니다.")
    private String originUrl;
}
