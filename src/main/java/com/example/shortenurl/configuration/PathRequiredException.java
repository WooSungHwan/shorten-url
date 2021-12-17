package com.example.shortenurl.configuration;

public class PathRequiredException extends RuntimeException {

    public PathRequiredException() {
        super();
    }

    public PathRequiredException(final String message) {
        super(message);
    }

}
