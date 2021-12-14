package com.example.shortenurl.repository;

import com.example.shortenurl.entity.ShortenUrl;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ShortenUrlRepository {

    private static final Map<Long, ShortenUrl> PERSISTENCE_CONTEXT = new HashMap<>();

    public void add(ShortenUrl shortenUrl) {
        PERSISTENCE_CONTEXT.put(getMaxKey(), shortenUrl);
    }

    public ShortenUrl get()

    private synchronized Long getMaxKey() {
        return Collections.max(PERSISTENCE_CONTEXT.keySet());
    }

}
