package com.example.shortenurl.service;

import com.example.shortenurl.data.OriginPath;
import com.example.shortenurl.data.OriginUrl;
import com.example.shortenurl.data.ShortenPath;
import com.example.shortenurl.data.ShortenUrl;
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

    private static final String REDIRECT_URL = "http://localhost:8080";

    public ShortenUrl getShortenUrl(final String url) {
        OriginPath originPath = OriginPath.of(url);
        long id = shortenUrlRepository.add(originPath);
        ShortenPath shortenPath = ShortenPath.of(REDIRECT_URL, shortenUrlGenerator.generate(id));
        return ShortenUrl.of(shortenPath, originPath);
    }

    public ShortenUrl getOriginalPath(final String shortenPath) {
        final Long id = shortenUrlGenerator.parse(shortenPath);
        final OriginUrl originUrl = shortenUrlRepository.get(id);
        return ShortenUrl.of(ShortenPath.of(shortenPath), originUrl.getOriginPath());
    }
}