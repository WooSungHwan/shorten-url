package com.example.shortenurl.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ShortenUrlRequest {
    @NotBlank(message = "원본 url을 입력해주시기 바랍니다.")
    private String originalUrl;
}
