package com.example.shortenurl.data;

import com.example.shortenurl.configuration.exception.VerifyException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.platform.commons.function.Try.success;

public class TestOriginUrl {

    @ParameterizedTest
    @MethodSource("generateClearOriginUrlParam")
    void clearOriginUrlParamTest(Long id, OriginPath originPath) {
        try {
            OriginUrl.of(id, originPath);
        } catch (VerifyException ve) {
            fail("clearOriginUrlParamTest is failed", ve);
        }
        success("Test Success!!");
    }

    @ParameterizedTest
    @MethodSource("generateNullOriginPathParam")
    void nullOriginPathParamTest(Long id, OriginPath originPath) {
        try {
            OriginUrl.of(id, originPath);
        } catch (VerifyException ve) {
            success("Test Success!!");
            return;
        }
        fail("nullOriginPathParamTest is failed : originPath must not be null!!");
    }

    @ParameterizedTest
    @MethodSource("generateIdNotPositiveParamParam")
    void idNotPositiveParamTest(Long id, OriginPath originPath) {
        try {
            OriginUrl.of(id, originPath);
        } catch (VerifyException ve) {
            success("Test Success!!");
            return;
        }
        fail("nullOriginPathParamTest is failed : id must be positive value!!");
    }

    static Stream<Arguments> generateClearOriginUrlParam() {
        return Stream.of(
                Arguments.of(1L, OriginPath.of("http://www.musinsa.com")),
                Arguments.of(2L, OriginPath.of("http://www.sas-study.tistory.com")),
                Arguments.of(3L, OriginPath.of("http://www.sas-study.tistory.com/1")),
                Arguments.of(4L, OriginPath.of("http://www.sas-study.tistory.com/2")),
                Arguments.of(5L, OriginPath.of("http://www.sas-study.tistory.com/3"))
        );
    }

    static Stream<Arguments> generateNullOriginPathParam() {
        return Stream.of(
                Arguments.of(1L, null)
        );
    }

    static Stream<Arguments> generateIdNotPositiveParamParam() {
        return Stream.of(
                Arguments.of(-1L, OriginPath.of("http://www.musinsa.com")),
                Arguments.of(0L, OriginPath.of("http://www.musinsa.com"))
        );
    }

}
