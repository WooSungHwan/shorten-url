package com.example.shortenurl.service;

import com.example.shortenurl.component.ShortenUrlGenerator;
import com.example.shortenurl.configuration.exception.VerifyException;
import com.example.shortenurl.data.OriginPath;
import com.example.shortenurl.data.OriginUrl;
import com.example.shortenurl.data.ShortenPath;
import com.example.shortenurl.data.ShortenUrl;
import com.example.shortenurl.repository.ShortenUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShortenUrlService {

    private final ShortenUrlRepository shortenUrlRepository;

    private final ShortenUrlGenerator shortenUrlGenerator;

    public ShortenUrl getShortenUrl(final OriginPath originPath) {
        final long id = shortenUrlRepository.isExistOriginPath(originPath) ? shortenUrlRepository.get(originPath).getId() : shortenUrlRepository.add(originPath);
        final ShortenPath shortenPath = ShortenPath.of(shortenUrlGenerator.generate(id));
        return ShortenUrl.of(shortenPath, originPath);
    }

    public OriginPath getOriginPath(final ShortenPath shortenPath) {
        final Long id = shortenUrlGenerator.parse(shortenPath);
        return Optional.ofNullable(shortenUrlRepository.get(id))
                .map(OriginUrl::getOriginPath)
                .orElseThrow(() -> new VerifyException("The request can not handled."));
    }
}