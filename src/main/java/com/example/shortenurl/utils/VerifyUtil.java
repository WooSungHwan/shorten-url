package com.example.shortenurl.utils;

import com.example.shortenurl.configuration.exception.VerifyException;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class VerifyUtil {

    /**
     * the expression is true, valid
     * @param expression
     * @param message
     */
    public static final void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new VerifyException(message);
        }
    }

    /**
     * the expression is false, valid
     * @param expression
     * @param message
     */
    public static final void isFalse(boolean expression, String message) {
        if (expression) {
            throw new VerifyException(message);
        }
    }

    /**
     * the expression is not null, valid
     * @param object
     * @param message
     */
    public static final void nonNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new VerifyException(message);
        }
    }
}
