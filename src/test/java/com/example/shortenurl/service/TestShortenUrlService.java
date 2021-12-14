package com.example.shortenurl.service;

import com.example.shortenurl.component.ShortenUrlGenerator;
import com.example.shortenurl.data.OriginUrl;
import com.example.shortenurl.data.ShortenUrl;
import com.example.shortenurl.repository.ShortenUrlRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestShortenUrlService {

    @Autowired
    ShortenUrlService shortenUrlService;

    @Autowired
    ShortenUrlRepository shortenUrlRepository;

    @Autowired
    ShortenUrlGenerator shortenUrlGenerator;

    @MethodSource("generateOriginUrls")
    @ParameterizedTest
    void getShortenUrl(String originPath) throws Exception {
        // when
        ShortenUrl shortenUrl = shortenUrlService.getShortenUrl(originPath);

        // then
        Long id = shortenUrlGenerator.parse(shortenUrl.getShortenPath().getPath());
        OriginUrl originUrl = shortenUrlRepository.get(id);
        assertThat(originUrl).extracting("originPath").extracting("path").isEqualTo(originPath);
    }

    static Stream<String> generateOriginUrls() {
        return Stream.of(
                "http://www.naver.com",
                "https://www.naver.com",
                "http://sas-study.tistory.com",
                "https://sas-study.tistory.com"
        );
    }

}
