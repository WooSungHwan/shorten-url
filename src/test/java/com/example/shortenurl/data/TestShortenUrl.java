package com.example.shortenurl.data;

import com.example.shortenurl.configuration.exception.VerifyException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.platform.commons.function.Try.success;

public class TestShortenUrl {

    @ParameterizedTest
    @MethodSource("generateClearShortenUrlParam")
    void clearShortenUrlParamTest(ShortenPath shortenPath, OriginPath originPath) {
        try {
            ShortenUrl.of(shortenPath, originPath);
        } catch (VerifyException ve) {
            fail("clearShortenUrlParamTest is failed", ve);
        }
        success("Test Success!!");
    }

    @ParameterizedTest
    @MethodSource("generateNullOriginPathParam")
    void nullOriginPathParamTest(ShortenPath shortenPath, OriginPath originPath) {
        try {
            ShortenUrl.of(shortenPath, originPath);
        } catch (VerifyException ve) {
            success("Test Success!!");
            return;
        }
        fail("nullOriginPathParamTest is failed : originPath must not be null!!");
    }

    @ParameterizedTest
    @MethodSource("generateNullShortenPathParam")
    void nullShortenPathParamTest(ShortenPath shortenPath, OriginPath originPath) {
        try {
            ShortenUrl.of(shortenPath, originPath);
        } catch (VerifyException ve) {
            success("Test Success!!");
            return;
        }
        fail("nullOriginPathParamTest is failed : shortenPath must not be null!!");
    }

    static Stream<Arguments> generateClearShortenUrlParam() {
        return Stream.of(
                Arguments.of(ShortenPath.of("http://localhost/EQDKd"), OriginPath.of("http://www.musinsa.com")),
                Arguments.of(ShortenPath.of("http://localhost/EQDKf"), OriginPath.of("http://www.sas-study.tistory.com")),
                Arguments.of(ShortenPath.of("http://localhost/EQDKe"), OriginPath.of("http://www.sas-study.tistory.com/1")),
                Arguments.of(ShortenPath.of("http://localhost/EQDKq"), OriginPath.of("http://www.sas-study.tistory.com/2")),
                Arguments.of(ShortenPath.of("http://localhost/EQDKb"), OriginPath.of("http://www.sas-study.tistory.com/3"))
        );
    }

    static Stream<Arguments> generateNullOriginPathParam() {
        return Stream.of(
                Arguments.of(ShortenPath.of("http://localhost/EQDKd"), null)
        );
    }

    static Stream<Arguments> generateNullShortenPathParam() {
        return Stream.of(
                Arguments.of(null, OriginPath.of("http://www.musinsa.com"))
        );
    }
}
