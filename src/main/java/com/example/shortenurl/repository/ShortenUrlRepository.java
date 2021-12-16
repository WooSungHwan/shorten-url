package com.example.shortenurl.repository;

import com.example.shortenurl.data.OriginUrl;
import com.example.shortenurl.data.OriginPath;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class ShortenUrlRepository {

    private final Map<Long, OriginUrl> persistenceContext;

    public ShortenUrlRepository() {
        this.persistenceContext = new ConcurrentHashMap<>();
    }

    public synchronized long add(OriginPath originPath) {
        Long id = generateKey();
        persistenceContext.put(id, OriginUrl.of(id, originPath));
        return id;
    }

    public synchronized OriginUrl get(long id) {
        return persistenceContext.get(id);
    }

    public synchronized boolean isExistOriginPath(OriginPath originPath) {
        return persistenceContext.values()
                .stream()
                .anyMatch(originUrl -> originUrl.equalsOriginPath(originPath));
    }

    public synchronized OriginUrl get(OriginPath originPath) {
        return persistenceContext.values()
                .stream()
                .filter(originUrl -> originUrl.equalsOriginPath(originPath))
                .findFirst()
                .orElse(null);
    }

    private Long generateKey() {
        if (MapUtils.isEmpty(persistenceContext)) {
            return 100000000L;
        }
        return persistenceContext.keySet()
                .stream()
                .max(Comparator.comparing(key -> key))
                .map(max -> max + 1)
                .orElseThrow(NoSuchElementException::new);
    }

}
