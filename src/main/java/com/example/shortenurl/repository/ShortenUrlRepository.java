package com.example.shortenurl.repository;

import com.example.shortenurl.entity.ShortenUrl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Repository
public class ShortenUrlRepository {

    private static final Map<String, LinkedList<ShortenUrl>> PERSISTENCE_CONTEXT = new HashMap<>();

    public void add(String shortenUrlKey, ShortenUrl shortenUrl) {
        LinkedList<ShortenUrl> shortenUrls = null;
        if (PERSISTENCE_CONTEXT.containsKey(shortenUrlKey)) {
             shortenUrls = PERSISTENCE_CONTEXT.get(shortenUrlKey);
        } else {
            shortenUrls = new LinkedList<>();
        }
        shortenUrls.add(shortenUrl);
        PERSISTENCE_CONTEXT.put(shortenUrlKey, shortenUrls);
    }
}
