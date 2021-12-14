package com.example.shortenurl.repository;

import com.example.shortenurl.data.OriginUrl;
import com.example.shortenurl.data.OriginPath;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ShortenUrlRepository {

    public final Map<Long, OriginUrl> persistenceContext;

    public ShortenUrlRepository() {
        this.persistenceContext = new ConcurrentHashMap<>();
    }

    public long add(OriginPath originPath) {
        OriginUrl originUrl = get(originPath);
        if (Objects.isNull(originUrl)) {
            return addOriginPathReturningId(originPath);
        }
        return originUrl.getId();
    }

    public OriginUrl get(long id) {
        return persistenceContext.get(id);
    }

    private OriginUrl get(OriginPath originPath) {
        return persistenceContext.values()
                .stream()
                .filter(value -> value.equalsOriginPath(originPath))
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
                .orElseThrow(NoSuchElementException::new);
    }

    private Long addOriginPathReturningId(OriginPath originPath) {
        Long id = generateKey();
        persistenceContext.put(id, OriginUrl.of(id, originPath));
        return id;
    }

}
