package com.example.shortenurl.service;

import com.example.shortenurl.component.ShortenUrlGenerator;
import com.example.shortenurl.configuration.exception.VerifyException;
import com.example.shortenurl.data.OriginPath;
import com.example.shortenurl.data.OriginUrl;
import com.example.shortenurl.data.ShortenPath;
import com.example.shortenurl.data.ShortenUrl;
import com.example.shortenurl.repository.ShortenUrlRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.platform.commons.function.Try.success;

@SpringBootTest
public class TestShortenUrlService {

    @Autowired
    ShortenUrlService shortenUrlService;

    @Autowired
    ShortenUrlRepository shortenUrlRepository;

    @Autowired
    ShortenUrlGenerator shortenUrlGenerator;

    @DisplayName("[ShortenUrlService] 클래스의 getShortenUrl 메소드에 대한 테스트")
    @MethodSource("generateOriginUrls")
    @ParameterizedTest
    void getShortenUrl(String originPath) throws Exception {
        // when
        ShortenUrl shortenUrl = shortenUrlService.getShortenUrl(OriginPath.of(originPath));

        // then
        Long id = shortenUrlGenerator.parse(shortenUrl.getShortenPath());
        OriginUrl originUrl = shortenUrlRepository.get(id);
        assertThat(originUrl).extracting("originPath").extracting("path").isEqualTo(originPath);

        success("Test Success!!");
    }

    @DisplayName("[ShortenUrlService] 클래스의 getOriginalPath 메소드에 대한 테스트")
    @MethodSource("generateOriginUrls")
    @ParameterizedTest
    void getOriginalPath(String originPath) {
        // given
        OriginPath givenOriginPath = OriginPath.of(originPath);
        final long id = shortenUrlRepository.add(givenOriginPath);
        final ShortenPath shortenPath = ShortenPath.of(shortenUrlGenerator.generate(id));

        // when
        final OriginPath whenOriginPath = shortenUrlService.getOriginPath(shortenPath);

        // then
        assertThat(whenOriginPath).isEqualTo(givenOriginPath);

        success("Test Success!!");
    }

    @DisplayName("[ShortenUrlService] 클래스의 getOriginalPath 메소드에 대한 부재 테스트")
    @Test
    void getAbsence() {
        // given
        String absentPath = "http://localhost/A"; // 100000000L 부터 시작할 것이기 때문에 나올 수 없음.
        ShortenPath absentShortenPath = ShortenPath.of(absentPath);

        // when
        VerifyException verifyException = assertThrows(VerifyException.class, () -> shortenUrlService.getOriginPath(absentShortenPath));

        // then
        assertThat(verifyException.getMessage()).isEqualTo("The request can not handled.");

        success("Test Success!!");
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
