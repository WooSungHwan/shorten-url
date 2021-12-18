package com.example.shortenurl.data;

import com.example.shortenurl.configuration.exception.VerifyException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.platform.commons.function.Try.success;

public class TestOriginPath {

    @ParameterizedTest
    @MethodSource("generateClearOriginPathParam")
    void clearOriginPathParamTest(String path) {
        try {
            OriginPath.of(path);
        } catch (VerifyException ve) {
            fail("clearOriginPathParamTest is failed", ve);
        }
        success("Test Success!!");
    }

    @ParameterizedTest
    @MethodSource("generateBlankOriginPathParam")
    void blankOriginPathParamTest(String path) {
        try {
            OriginPath.of(path);
        } catch (VerifyException ve) {
            success("Test Success!!");
            return;
        }
        fail("clearOriginPathParamTest is failed : A path value is required");
    }

    static Stream<String> generateClearOriginPathParam() {
        return Stream.of(
                "http://www.naver.com",
                "https://www.naver.com",
                "http://sas-study.tistory.com",
                "https://sas-study.tistory.com"
        );
    }

    static Stream<String> generateBlankOriginPathParam() {
        return Stream.of(
                null,
                "",
                "   ",
                " "
        );
    }

}
