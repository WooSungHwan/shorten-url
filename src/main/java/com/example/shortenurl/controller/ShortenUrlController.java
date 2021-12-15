package com.example.shortenurl.controller;

import com.example.shortenurl.data.ShortenUrl;
import com.example.shortenurl.request.ShortenUrlRequest;
import com.example.shortenurl.service.ShortenUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/shorten-url")
@RestController
public class ShortenUrlController {

    private final ShortenUrlService shortenUrlService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShortenUrl> getShortenUrl(@RequestBody @Validated final ShortenUrlRequest request) {
        return ResponseEntity.ok(shortenUrlService.getShortenUrl(request.getOriginUrl()));
    }

    @GetMapping(value = "/{shortenPath}")
    public ResponseEntity<Object> redirect(@PathVariable("shortenPath") final String shortenPath) {
        final ShortenUrl shortenUrl = shortenUrlService.getOriginalPath(shortenPath);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(shortenUrl.toOriginUrl())
                .build();
    }

}
