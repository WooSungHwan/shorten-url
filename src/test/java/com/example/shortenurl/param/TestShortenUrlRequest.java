package com.example.shortenurl.param;

import com.example.shortenurl.URL;
import com.example.shortenurl.request.ShortenUrlRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestShortenUrlRequest extends TestAbstractRequest {

    @ParameterizedTest
    @MethodSource("generateNoUrlParam")
    void URL_형식_불일치(String originUrl) throws Exception {
        ShortenUrlRequest request = makeRequest(originUrl);

        Set<ConstraintViolation<ShortenUrlRequest>> validate = validator.validate(request);
        assertThat(validate).hasSize(1);

        String message = (String) URL.class.getDeclaredMethod("message").getDefaultValue();

        validate.forEach(e -> {
            log.info("에러메시지 : {}", e.getConstraintDescriptor().getMessageTemplate());
            assertThat(e.getConstraintDescriptor().getMessageTemplate()).isEqualTo(message);
        });
    }

    static Stream<String> generateNoUrlParam() {
        return Stream.of(
                "sjdfklsjdlf",
                "gjksfljjkldsfjlsjf.com",
                "s.t.t.t.t.t",
                "dkjflj.dkfjlsdf.cjsdlfjdls",
                "www.naver..com"
        );
    }

    @ParameterizedTest
    @MethodSource("generateEmptyUrlParam")
    void URL_빈값(String originUrl) throws Exception {
        ShortenUrlRequest request = makeRequest(originUrl);

        Set<ConstraintViolation<ShortenUrlRequest>> validate = validator.validate(request);
        assertThat(validate).hasSize(1);

        String notBlankMessage = ShortenUrlRequest.class.getDeclaredField("originUrl").getAnnotation(NotBlank.class).message();

        validate.forEach(e -> {
            log.info("에러메시지 : {}", e.getConstraintDescriptor().getMessageTemplate());
            assertThat(e.getConstraintDescriptor().getMessageTemplate()).isEqualTo(notBlankMessage);
        });
    }

    static Stream<String> generateEmptyUrlParam() {
        return Stream.of("", " ", null);
    }
}
