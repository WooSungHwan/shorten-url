package com.example.shortenurl.data;

import com.example.shortenurl.configuration.exception.VerifyException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.platform.commons.function.Try.success;

public class TestShortenPath {

    @ParameterizedTest
    @MethodSource("generateClearShortenPathParam")
    void clearShortenPathParamTest(String path) {
        try {
            ShortenPath.of(path);
        } catch (VerifyException ve) {
            fail("clearShortenPathParamTest is failed", ve);
        }
        success("Test Success!!");
    }

    @ParameterizedTest
    @MethodSource("generateBlankShortenPathParam")
    void blankShortenPathParamTest(String path) {
        try {
            ShortenPath.of(path);
        } catch (VerifyException ve) {
            success("Test Success!!");
            return;
        }
        fail("clearShortenPathParamTest is failed : A path value is required");
    }

    static Stream<String> generateClearShortenPathParam() {
        return Stream.of(
                "http://localhost/EQDKd",
                "http://localhost/EQDKf",
                "http://localhost/EQDKe",
                "http://localhost/EQDKq",
                "http://localhost/EQDKb"
        );
    }

    static Stream<String> generateBlankShortenPathParam() {
        return Stream.of(
                null,
                "",
                "   ",
                " "
        );
    }

}
