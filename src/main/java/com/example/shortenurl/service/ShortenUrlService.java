package com.example.shortenurl.service;

import com.example.shortenurl.entity.ShortenUrl;
import com.example.shortenurl.repository.ShortenUrlRepository;
import com.example.shortenurl.component.ShortenUrlGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShortenUrlService {

    private final ShortenUrlRepository shortenUrlRepository;
    private final ShortenUrlGenerator shortenUrlGenerator;

    public ShortenUrl getShortenUrl(String originUrl) {
        // originUrl db에 넣기

        // long값 id로
        return null;
    }
}