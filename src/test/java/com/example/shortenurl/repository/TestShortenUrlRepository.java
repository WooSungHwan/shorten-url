package com.example.shortenurl.repository;

import com.example.shortenurl.data.OriginPath;
import com.example.shortenurl.data.OriginUrl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.commons.function.Try.success;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TestShortenUrlRepository {

    @InjectMocks
    private ShortenUrlRepository shortenUrlRepository;

    @DisplayName("[ShortenUrlRepository] 클래스의 add 메소드에 대한 테스트")
    @ParameterizedTest
    @MethodSource("generateOriginUrls")
    void add(String originPath) {
        // given
        final OriginPath givenOriginPath = OriginPath.of(originPath);

        // when
        final long id = shortenUrlRepository.add(givenOriginPath);
        final OriginUrl originUrl = shortenUrlRepository.get(id);

        // then
        assertThat(originUrl.getOriginPath()).isEqualTo(givenOriginPath);
        success("테스트 성공!!");
    }

    static Stream<String> generateOriginUrls() {
        return Stream.of(
                "http://www.naver.com",
                "https://www.naver.com",
                "http://sas-study.tistory.com",
                "https://sas-study.tistory.com"
        );
    }

    @DisplayName("[ShortenUrlRepository] 클래스의 get 메소드에 대한 테스트")
    @ParameterizedTest
    @MethodSource("generateOriginUrls")
    void get(String originPath) {
        // given
        final OriginPath givenOriginPath = OriginPath.of(originPath);
        final long id = shortenUrlRepository.add(givenOriginPath);

        // when
        final OriginPath whenOriginPath = shortenUrlRepository.get(id).getOriginPath();

        // then
        assertThat(givenOriginPath).isEqualTo(whenOriginPath);

        success("테스트 성공!!");
    }

    @DisplayName("[ShortenUrlRepository] 클래스의 get 메소드에 대한 동시성 테스트")
    @Test
    void concurrentGet() throws InterruptedException {
        // given
        List<Long> ids = addDummyData();
        ExecutorService executorService = Executors.newFixedThreadPool(11);

        // ready task
        List<Callable<OriginUrl>> callableTasks = new ArrayList<>();
        ids.forEach(id -> callableTasks.add(() -> shortenUrlRepository.get(id)));
        List<Future<OriginUrl>> futures = executorService.invokeAll(callableTasks);

        futures.parallelStream().forEach(future -> {
            try {
                // when
                OriginUrl originUrl = future.get();
                log.info("{} : {}", originUrl.getId(), originUrl.getOriginPath());

                // then
                assertThat(originUrl).isNotNull();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    @DisplayName("[ShortenUrlRepository] 클래스의 add 메소드에 대한 동시성 테스트")
    @Test
    void concurrentAdd() throws InterruptedException {
        // given
        ExecutorService executorService = Executors.newFixedThreadPool(11);

        // ready task
        List<OriginPath> originPaths = Arrays.stream(getOriginPaths()).collect(Collectors.toUnmodifiableList());
        List<Callable<Long>> callableTasks = new ArrayList<>();
        originPaths.forEach(originPath -> callableTasks.add(() -> shortenUrlRepository.add(originPath)));

        List<Future<Long>> futures = executorService.invokeAll(callableTasks);

        futures.parallelStream().forEach(future -> {
            try {
                Long id = future.get();
                // when
                OriginUrl originUrl = shortenUrlRepository.get(id);

                log.info("{} : {}", id, originUrl.getOriginPath());

                // then
                assertThat(id).isNotNull();
                assertThat(originUrl).extracting("id").isEqualTo(id);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

    }

    private List<Long> addDummyData() {
        List<OriginPath> originPaths = List.of(getOriginPaths());

        return originPaths.stream()
                .map(originPath -> shortenUrlRepository.add(originPath))
                .collect(Collectors.toUnmodifiableList());
    }

    private OriginPath[] getOriginPaths() {
        return new OriginPath[]{
                OriginPath.of("http://www.naver.com"),
                OriginPath.of("https://www.naver.com"),
                OriginPath.of("http://sas-study.tistory.com"),
                OriginPath.of("https://sas-study.tistory.com"),
                OriginPath.of("https://sas-study.tistory.com/1"),
                OriginPath.of("https://sas-study.tistory.com/2"),
                OriginPath.of("https://sas-study.tistory.com/3"),
                OriginPath.of("https://sas-study.tistory.com/4"),
                OriginPath.of("https://sas-study.tistory.com/5"),
                OriginPath.of("https://sas-study.tistory.com/6"),
                OriginPath.of("https://sas-study.tistory.com/7")};
    }

    @DisplayName("[ShortenUrlRepository] 클래스의 add 메소드에 대한 동시성 테스트")
    @Test
    void concurrencyAdd() {

    }

}
